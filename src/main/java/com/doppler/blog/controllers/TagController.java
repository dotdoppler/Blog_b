package com.doppler.blog.controllers;

import com.doppler.blog.Service.HashtagService;
import com.doppler.blog.Service.PostService;
import com.doppler.blog.models.Hashtag;
import com.doppler.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by doppler on 2016/5/31.
 */
@Controller
@RequestMapping("hashtag")
public class TagController {
    @Resource
    private HashtagService hashtagService;
    @Resource
    private PostService postService;

    @RequestMapping(value = "all",method = GET)
    public String AllTag(Model model){
        List<Hashtag> hashtags = hashtagService.findAll();
        model.addAttribute("allHashtags",hashtags);
        return "hashtags/index";
    }


    @RequestMapping(value = "{tagName}",method = GET)
        public String showPosts(@PathVariable String tagName,Model model){
        Hashtag hashtag = hashtagService.findByName(tagName);
        model.addAttribute("hashtag",hashtag.getName());
        List<Post> posts = postService.getPostsByTag(hashtag.getName());
        model.addAttribute("posts",posts);
        return "hashtags/list";
    }
}


