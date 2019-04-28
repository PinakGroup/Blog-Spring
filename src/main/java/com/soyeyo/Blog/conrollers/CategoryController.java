package com.soyeyo.Blog.conrollers;

import com.soyeyo.Blog.data.CategoryRepository;
import com.soyeyo.Blog.dto.Pagination.PaginationDTO;
import com.soyeyo.Blog.errors.InvalidUpdateException;
import com.soyeyo.Blog.models.Category;
import com.soyeyo.Blog.services.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categories;


    @RequestMapping(method = RequestMethod.GET)
    public PaginationDTO getCategories(@RequestParam(defaultValue = "1" ) String page,
                                       @RequestParam( defaultValue = "") String sort,
                                       @RequestParam(defaultValue = "10")String per_page){
        return PaginationService.<Category>getPagination(page,sort,per_page,categories,"categories");

    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        if(!category.isValid()) throw new InvalidUpdateException("Name and description should be provided");
        return categories.save(category);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Category getCategory(@PathVariable int id){
        Optional<Category> optionalCategory = categories.findById(id);
        return optionalCategory.orElse(null);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Category updateCategory(@PathVariable int id ,@RequestBody Category category){
        category.setCategoryId(id);
        if(!category.isValidUpdate(categories))throw new InvalidUpdateException("Invalid Request parameters : ensure name is not empty and id is valid");
        Category currentCategory = categories.findById(id).get();
        currentCategory.setName(category.getName());
        return categories.save(currentCategory);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    private boolean deleteCategory(@PathVariable int id){
         try {
             categories.deleteById(id);
         }catch (Exception e){
             throw new InvalidUpdateException("Did not delete");
         }
        return true;
    }

    //TODO
    //get all posts in a category

}
