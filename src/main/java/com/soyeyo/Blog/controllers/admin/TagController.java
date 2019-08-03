package com.soyeyo.Blog.controllers.admin;

import com.soyeyo.Blog.data.TagRepository;
import com.soyeyo.Blog.dto.admin.PaginationDTO;
import com.soyeyo.Blog.errors.InvalidUpdateException;
import com.soyeyo.Blog.models.Tag;
import com.soyeyo.Blog.services.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("admin/tags")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class TagController {

    @Autowired
    TagRepository tags;

    @RequestMapping(method = RequestMethod.GET)
    public PaginationDTO getTags(@RequestParam(defaultValue = "1" ) String page,
                                 @RequestParam( defaultValue = "") String sort,
                                 @RequestParam(defaultValue = "10")String per_page){
        return PaginationService.<Tag>getPagination(page,sort,per_page,tags,"tags");
    }

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag){
       if(tag.getName().equals("")) throw new InvalidUpdateException("Name must be provided");
       return tags.save(tag);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public Tag getTag(@PathVariable int id){
        Optional<Tag> optionalTag = tags.findById(id);
        return optionalTag.orElse(null);
    }

    @RequestMapping(method = RequestMethod.PUT,path = "/{id}")
    public Tag updateTag(@PathVariable int id,@RequestBody Tag tag){
        if(tag.getName().equals(""))throw  new InvalidUpdateException("Name must be provided");
        Tag currentTag = tags.findById(id).get();
        currentTag.setName(tag.getName());
        return tags.save(currentTag);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    private boolean deleteTag(@PathVariable int id){
        try {
            tags.deleteById(id);
        }catch (Exception e){
            throw new InvalidUpdateException("Did not delete");
        }
        return true;
    }

    //TODO  get all posts with tag
}
