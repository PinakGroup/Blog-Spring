package com.soyeyo.Blog.data;

import com.soyeyo.Blog.models.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CommentRepository extends PagingAndSortingRepository<Comment,Integer> {
}
