package com.soyeyo.Blog.data;

import com.soyeyo.Blog.models.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag,Integer> {

    Iterable<Tag> findAllByNameLike(String name);
}
