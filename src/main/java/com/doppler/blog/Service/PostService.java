package com.doppler.blog.Service;

import com.doppler.blog.cache.RedisDao;
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
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;


/**
 * Created by doppler on 2016/5/23.
 */
@Service
public class PostService {

    @Resource
    private PostMapper postMapper;
    @Resource
    private HashtagService hashtagService;
    @Resource
    private RedisDao redisDao;

    private static final String CACHE_POST_ARCHIVE = "post_archive";
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    @Transactional
    public void createPost(PostForm postForm){

        Post post = DTOUtil.map(postForm, Post.class);
        post.setCreatedAt(DateFormatter.format(new Date()));
        if (post.getPostFormat() == PostFormat.MARKDOWN) {
            post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
        }
        post.setCreatedAt(DateFormatter.format(new Date()));
        int count = postMapper.insertPost(post);
        checkState(count == 1,INSERT_POST_FAIL.val());
        logger.info(INSERT_POST.val() + post.getTitle());
        Set<String> tagNames = parseHashtagStr(postForm.getHashtags());
        List<Long> hashtagIds = new ArrayList<>();
        checkNotNull(tagNames).forEach(name -> hashtagIds.add(hashtagService.findOrCreateByName(name).getId()));
        hashtagIds.forEach(hashtagId -> hashtagService.savePostAndTags(hashtagId,post.getId()));
    }


    @Cacheable(value = CACHE_POST_ARCHIVE)
    public List<Post> getPublishedPosts(){
        try {
            return checkNotNull(redisDao.getPostArchives(),CACHE_MSG_NULL.val());
        }catch (NullPointerException ex){
            logger.info(ex.getMessage());
        }

        List<Post> posts = postMapper.findAllPostsByStatus(PostStatus.PUBLISHED);
        redisDao.putPostArchives(posts);
        logger.info(CACHE_MSG_FROM_DB.val());
        return posts;
    }


    public Post getPostById(Long postId){
        return  postMapper.getPostById(postId);
    }


    public Post getByLink(String postLink){
        return checkNotNull(postMapper.getByLink(postLink),"Post Not Found");
    }


    public List<Post> findAllPosts(){
        return postMapper.findAllPosts();
    }



    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    public void deletePost(String postId){
        checkState(postMapper.deletePostById(postId) == 1,DELETE_POST_FAIL.val());
        logger.info(DELETE_POST.val() + postId);
    }


    @CacheEvict(value = CACHE_POST_ARCHIVE, allEntries = true)
    @Transactional
    public void updatePost(Long postId,PostForm postForm){
        Post post = getPostById(postId);
        DTOUtil.mapTo(postForm, post);
        if (post.getPostFormat() == PostFormat.MARKDOWN) {
            post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
        }
        post.setUpdatedAt(DateFormatter.format(new Date()));
        int count = postMapper.updatePost(post);
        checkState(count == 1,UPDATE_POST_FAIL.val());
        logger.info(UPDATE_POST.val() + post.getTitle());
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
        if (hashtags == null || hashtags.isEmpty()) {
            return "";
        }
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


    private void deleteTagsForPost(List<Long> ids,Long postId){
    ids.forEach(id -> postMapper.deleteTag(id,postId));
    }
}
