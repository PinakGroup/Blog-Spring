package com.soyeyo.Blog.data;

import com.soyeyo.Blog.models.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post,Integer> {
}
