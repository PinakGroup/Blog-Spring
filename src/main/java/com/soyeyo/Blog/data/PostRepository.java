package com.soyeyo.Blog.data;

import com.soyeyo.Blog.models.Post;
import javafx.geometry.Pos;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<Post,Integer> {
    Iterable<Post> findPostsByFeatured(boolean featured);

    Optional<Post> findPostBySlug(String slug);
}
