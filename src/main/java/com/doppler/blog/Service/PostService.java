package com.doppler.blog.Service;

import com.doppler.blog.exception.CommonException;
import com.doppler.blog.forms.PostForm;
import com.doppler.blog.mappers.PostMapper;
import com.doppler.blog.models.Post;
import com.doppler.blog.models.support.PostFormat;
import com.doppler.blog.models.support.PostStatus;
import com.doppler.blog.utils.DTOUtil;
import com.doppler.blog.utils.DateFormatter;
import com.doppler.blog.utils.Markdown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.doppler.blog.GlobalConstants.*;


/**
 * Created by doppler on 2016/5/23.
 */
@Service
public class PostService {

    @Resource
    private PostMapper postMapper;
    @Resource
    HashtagService hashtagService;

    private static final String CACHE_POST_ARCHIVE = "post_archive";
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    @Transactional
    public void createPost(PostForm postForm) throws CommonException {

        Post post = DTOUtil.map(postForm, Post.class);
        post.setCreatedAt(DateFormatter.format(new Date()));
        if (post.getPostFormat() == PostFormat.MARKDOWN) {
            post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
        }
        post.setCreatedAt(DateFormatter.format(new Date()));
        int count = postMapper.insertPost(post);
            if(count != 1)
                throw new CommonException(INSERT_POST_FAIL.value());
        logger.info(INSERTPOST.value() + post.getTitle());
        Set<String> tagNames = parseHashtagStr(postForm.getHashtags());
        List<Long> hashtagIds = new ArrayList<>();
        if (tagNames != null)
            tagNames.forEach(name -> hashtagIds.add(hashtagService.findOrCreateByName(name).getId()));
        hashtagIds.forEach(hashtagId -> hashtagService.savePostAndTags(hashtagId,post.getId()));
    }


    @Cacheable(value = CACHE_POST_ARCHIVE)
    public List<Post> getPublishedPosts(){
        logger.info("not cache,get post archive form db");
        return postMapper.findAllPostsByStatus(PostStatus.PUBLISHED);
    }


    public Post getPostById(Long postId){
        return  postMapper.getPostById(postId);
    }


    public Post getByLink(String postLink){
        return postMapper.getByLink(postLink);
    }


    public List<Post> findAllPosts(){
        return postMapper.findAllPosts();
    }



    @Transactional
    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    public void deletePost(String postId){
        postMapper.deletePostById(postId);
        logger.info(DELETEPOST.value() + postId);
    }


    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    @Transactional
    public void updatePost(Long postId,PostForm postForm){
        Post post = getPostById(postId);
        DTOUtil.mapTo(postForm, post);
        if (post.getPostFormat() == PostFormat.MARKDOWN)
            post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
        post.setUpdatedAt(DateFormatter.format(new Date()));
        postMapper.updatePost(post);
        logger.info(UPDATEPOST.value() + post.getTitle());
        Set<String> tagNames = parseHashtagStr(postForm.getHashtags());
        List<Long> hashtagIds = new ArrayList<>();
        List<String> tagsBeforeUpdate = getHashtags(postId);
        hashtagIds.addAll(tagsBeforeUpdate.stream().filter(s -> !tagNames.contains(s)).
                map(s -> hashtagService.findOrCreateByName(s).getId()).collect(Collectors.toList()));
        deleteTagsForPost(hashtagIds,postId);
        tagNames.stream().filter(s -> !tagsBeforeUpdate.contains(s)).
                forEach(s -> hashtagService.savePostAndTags(hashtagService.findOrCreateByName(s).getId(), postId));
    }


    public List<Post> getRecentPosts(){
        return postMapper.getRecentPosts();
    }


    public Set<String> parseHashtagStr(String hashtags_str){
        if(hashtags_str != null && !hashtags_str.isEmpty()){
            String names[] = hashtags_str.split("\\s*,\\s*");
            Set<String> set = new HashSet<>();
            for (int i = 0;i < names.length;i++)
                set.add(names[i]);
            return set;
        }
        else
            return null;
    }


    public String getHashtags_str(List<String> hashtags) {
        if (hashtags == null || hashtags.isEmpty())
            return "";
        StringBuilder hashtags_str = new StringBuilder("");
        hashtags.forEach(hashtag -> hashtags_str.append(hashtag).append(","));
        hashtags_str.deleteCharAt(hashtags_str.length() - 1);
        return hashtags_str.toString();
    }


    public List<Post> getPostsByTag(String tagName) {

       return postMapper.getPostsByHashtag(tagName);
    }


    public List<String> getHashtags(Long postId) {
        return postMapper.getHashtags(postId);
    }


    @Transactional
    private void deleteTagsForPost(List<Long> ids,Long postId){
    ids.forEach(id -> postMapper.deleteTag(id,postId));
    }
}
