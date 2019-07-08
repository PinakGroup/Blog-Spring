package com.soyeyo.Blog.data;

import com.soyeyo.Blog.models.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {
}
