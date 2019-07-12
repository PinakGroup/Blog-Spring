package com.soyeyo.Blog.controllers;


import com.soyeyo.Blog.data.PostRepository;
import com.soyeyo.Blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RequestMapping("featured")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class FeaturedController {
    @Autowired
    PostRepository posts;


    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Post> getFeatured() {
        ArrayList<Post> featuredPosts = new ArrayList<>();
        posts.findPostsByFeatured(true).forEach(post->{
            post.getCategory().setPosts(new ArrayList<>());
            featuredPosts.add(post);
        });
        return featuredPosts;
    }
}
