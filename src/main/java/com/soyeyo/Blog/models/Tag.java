package com.soyeyo.Blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @NotBlank
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "tags")
    private List<Post> posts;

    public Tag() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
}
