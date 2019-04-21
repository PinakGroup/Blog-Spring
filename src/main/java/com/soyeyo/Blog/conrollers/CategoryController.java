package com.soyeyo.Blog.conrollers;

import com.soyeyo.Blog.data.CategoryRepository;
import com.soyeyo.Blog.errors.InvalidUpdateException;
import com.soyeyo.Blog.models.Category;
import com.soyeyo.Blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categories;

    @Value("${pagination.size.admin}")
    private int TOPIC_PER_PAGE;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Category> getCategories(@RequestParam(defaultValue = "0" ) String page){
       int page_no;
        try {
          page_no = Integer.parseInt(page);
      }catch (Exception e){
          page_no = 0;
      }

      if(page_no == 0)return categories.findAll();
      return categoryService.formatAll(categories.findAll(PageRequest.of(page_no,TOPIC_PER_PAGE)));
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

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Category updateCategory(@RequestBody Category category){
        if(!category.isValidUpdate(categories))throw new InvalidUpdateException("Invalid Request parameters : ensure name is not empty and id is valid");
        Category currentCategory = categories.findById(category.getCategoryId()).get();
        currentCategory.setName(category.getName());
        return currentCategory;
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
