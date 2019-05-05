package com.soyeyo.Blog.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soyeyo.Blog.data.CategoryRepository;
import com.soyeyo.Blog.data.TagRepository;
import com.soyeyo.Blog.models.Category;
import com.soyeyo.Blog.models.Post;
import com.soyeyo.Blog.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PostDTO {
    private Post post;
    private List<Integer> tags;
    private int categoryId;

    public PostDTO(){
        tags = new ArrayList<>();
    }

    public PostDTO(Post post, List<Integer> tags,int categoryId) {
        this.post = post;
        this.tags = tags;
        this.categoryId = categoryId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }



    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    private void setTagsToPost(TagRepository tagRepository) {
        if(post.getTags() == null)post.setTags(new ArrayList<>());
        tags.forEach(tagId->{
            Optional<Tag> optionalTag = tagRepository.findById(tagId);
            if(optionalTag.isPresent()){
                Tag tag = optionalTag.get();
                post.getTags().add(tag);
            }
        });
    }


    public void setupPost(TagRepository tagRepository,CategoryRepository categoryRepository) {
        setTagsToPost(tagRepository);
        setCategoryToPost(categoryRepository);
    }

    private void setCategoryToPost(CategoryRepository categoryRepository) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            this.post.setCategory(category);
        }
    }
}
