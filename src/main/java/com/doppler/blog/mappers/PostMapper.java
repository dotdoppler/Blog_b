package com.doppler.blog.mappers;

import com.doppler.blog.models.Post;
import com.doppler.blog.models.support.PostStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by doppler on 2016/7/3.
 */
@Mapper
public interface PostMapper {

    List<Post> findAllPostsByStatus(@Param("postStatus") PostStatus postStatus);

    Post getPostById(@Param("postId") Long postId);

    Post getByLink(@Param("postLink") String postLink);

    List<Post> findAllPosts();

    List<Post> getPostsByHashtag(@Param("tagName") String tagName);

    int insertPost(Post post);

    int deletePostById(@Param("postId") String postId);

    int updatePost(Post post);

    List<String> getHashtags(@Param("postId") Long postId);

    void deleteTag(@Param("tagId") Long id,@Param("postId") Long postId);

    List<Post> getRecentPosts();
}
