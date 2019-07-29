package com.soyeyo.Blog.controllers.admin;

import com.soyeyo.Blog.data.CategoryRepository;
import com.soyeyo.Blog.data.PostRepository;
import com.soyeyo.Blog.data.TagRepository;
import com.soyeyo.Blog.dto.admin.PostDTO;
import com.soyeyo.Blog.errors.InvalidUpdateException;
import com.soyeyo.Blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@RequestMapping("admin/posts")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class AdminPostController {

    @Autowired
    PostRepository posts;

    @Autowired
    TagRepository tags;

    @Autowired
    CategoryRepository categories;

    @Value("${posts.featured.num}")
    long featuredNum;


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Post addPost(@RequestBody PostDTO postDTO){
        postDTO.setupPost(tags,categories);
        if(!postDTO.getPost().isValid()) throw new InvalidUpdateException("Bad format");
        return posts.save(postDTO.getPost());
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Post getPostById(@PathVariable int id) throws Exception {
        Optional<Post> optionalPost = posts.findById(id);
        if(!optionalPost.isPresent()) throw new Exception("Post not present");
        Post post = optionalPost.get();
        post.getCategory().setPosts(new ArrayList<>());
        return post;
    }




    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Post updatePost(@PathVariable int id ,@RequestBody PostDTO postDTO){
        postDTO.setupPost(tags,categories);
        if(!postDTO.getPost().isValid())throw new InvalidUpdateException("Invalid Request parameters : ensure all fields have values");
        postDTO.getPost().setPostId(id);
        return posts.save(postDTO.getPost());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    private boolean deletePost(@PathVariable int id){
        try {
            posts.deleteById(id);
        }catch (Exception e){
            throw new InvalidUpdateException("Did not delete");
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/featured/{id}")
    private ResponseEntity<Map> putFeatured(@PathVariable int id){
        long featuredN = 0;
        for(Post p : posts.findPostsByFeatured(true)){
            featuredN++;
        }
        System.out.println("count :"+featuredN);
        System.out.println("Set :"+this.featuredNum);
       if(featuredN >= this.featuredNum ){
           return ResponseEntity.badRequest()
                   .header("content-Type: application/json")
                   .body(Collections.singletonMap("message","Already featured maximum posts"));
       }

        Optional<Post> post = posts.findById(id);
        if(!post.isPresent()){
            return ResponseEntity.badRequest()
                    .header("content-Type: application/json")
                    .body(Collections.singletonMap("message","pos not found !"));
        }
        Post p = post.get();
        p.setFeatured(true);
        posts.save(p);
        return ResponseEntity.ok()
                .header("content-Type: application/json")
                .body(Collections.singletonMap("message","success"));
    }
}
