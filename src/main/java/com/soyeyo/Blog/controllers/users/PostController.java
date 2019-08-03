package com.soyeyo.Blog.controllers.users;


import com.soyeyo.Blog.data.PostRepository;
import com.soyeyo.Blog.data.TagRepository;
import com.soyeyo.Blog.dto.admin.PaginationDTO;
import com.soyeyo.Blog.dto.admin.PostDTO;
import com.soyeyo.Blog.models.Post;
import com.soyeyo.Blog.models.Tag;
import com.soyeyo.Blog.services.PaginationService;
import com.soyeyo.Blog.services.SearchPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestMapping("posts")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class PostController {

    @Autowired
    PostRepository posts;
    @Autowired
    TagRepository tags;

    @RequestMapping(method = RequestMethod.GET)
    public PaginationDTO getPosts(@RequestParam(defaultValue = "1" ) String page,
                                  @RequestParam( defaultValue = "") String sort,
                                  @RequestParam(defaultValue = "10")String per_page){
        return PaginationService.<PostDTO>getPagination(page,sort,per_page,posts,"posts");
    }

    @RequestMapping(path = "/{slug}",method = RequestMethod.GET)
    public Post getPostBySlug(@PathVariable String slug) throws Exception {
        Optional<Post> optionalPost = posts.findPostBySlug(slug);
        if(!optionalPost.isPresent()) throw new Exception("Post not present");
        Post post = optionalPost.get();
        post.getCategory().setPosts(new ArrayList<>());
        return post;
    }


    @RequestMapping(path = "/featured", method = RequestMethod.GET)
    public Iterable<Post> getFeatured() {
        ArrayList<Post> featuredPosts = new ArrayList<>();
        posts.findPostsByFeatured(true).forEach(post->{
            post.getCategory().setPosts(new ArrayList<>());
            featuredPosts.add(post);
        });
        return featuredPosts;
    }

    @RequestMapping(path = "/tags/{id}",method = RequestMethod.GET)
    public Iterable<Post> getByTag(@PathVariable int id,@RequestParam(defaultValue = "1" ) String page){
        Optional<Tag> tagO = tags.findById(id);
        if(!tagO.isPresent()){
            return new ArrayList<>();
        }
        return SearchPaginationService.getPagination(page,posts,tagO.get(),"","tag");

    }


    @RequestMapping(method = RequestMethod.GET,path = "/search/{key}")
    public Iterable<Post> searchPosts(@PathVariable String key,@RequestParam(defaultValue = "1" ) String page){
        System.out.println(key);
        return SearchPaginationService.getPagination(page,posts,null,key,"search");
    }

}
