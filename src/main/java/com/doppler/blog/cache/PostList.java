package com.doppler.blog.cache;

import com.doppler.blog.models.Post;

import java.util.List;

/**
 * Created by doppler on 2016/8/25.
 */
public class PostList {
    private List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
