package com.soyeyo.Blog.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.soyeyo.Blog.data.CategoryRepository;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "categories")

public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  categoryId;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Post> posts;

    @NotBlank
    private String description;

    public Category() {
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public boolean isValidUpdate(CategoryRepository repository) {
        boolean valid = isValid() && categoryId > 0 ;
        if(valid){
            return repository.findById(categoryId).isPresent();
        }
        return false;
    }

    public boolean isValid() {
        return name != null && !name.equals("") && description != null && !description.equals("");
    }
}
