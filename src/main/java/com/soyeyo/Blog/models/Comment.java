package com.soyeyo.Blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NotBlank @Type(type = "text")
    private String comment;

    @NotBlank
    private  String email;

    private boolean approved;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
