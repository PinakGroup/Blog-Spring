package com.soyeyo.Blog.controllers.admin;

import com.soyeyo.Blog.data.CategoryRepository;
import com.soyeyo.Blog.data.PostRepository;
import com.soyeyo.Blog.data.TagRepository;
import com.soyeyo.Blog.dto.admin.PaginationDTO;
import com.soyeyo.Blog.dto.admin.PostDTO;
import com.soyeyo.Blog.errors.InvalidUpdateException;
import com.soyeyo.Blog.models.Post;
import com.soyeyo.Blog.services.admin.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("admin/posts")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class PostController {

    @Autowired
    PostRepository posts;

    @Autowired
    TagRepository tags;

    @Autowired
    CategoryRepository categories;

    @RequestMapping(method = RequestMethod.GET)
    public PaginationDTO getPosts(@RequestParam(defaultValue = "1" ) String page,
                                       @RequestParam( defaultValue = "") String sort,
                                       @RequestParam(defaultValue = "10")String per_page){
        return PaginationService.<PostDTO>getPagination(page,sort,per_page,posts,"posts");
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Post addPost(@RequestBody PostDTO postDTO){
        postDTO.setupPost(tags,categories);
        if(!postDTO.getPost().isValid()) throw new InvalidUpdateException("Bad format");
        return posts.save(postDTO.getPost());
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Post getPost(@PathVariable int id){
        Optional<Post> optionalPost = posts.findById(id);
        return optionalPost.orElse(null);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Post updatePost(@PathVariable int id ,@RequestBody Post post){
        post.setPostId(id);
        if(!post.isValid())throw new InvalidUpdateException("Invalid Request parameters : ensure all fields have values");

        return posts.save(post);
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

}
