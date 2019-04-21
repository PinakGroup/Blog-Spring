package com.soyeyo.Blog.models;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @NotBlank
    private  String title;

    @NotBlank  @Type(type = "text")
    private String body;

    @NotBlank
    private String slug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "post_tag",joinColumns = {@JoinColumn(name = "post_post_id")},inverseJoinColumns = {@JoinColumn(name="tag_tag_id")})
    private List<Tag> tags;


    public Post(){}


    public int getPostId() {
        return postId;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



}
