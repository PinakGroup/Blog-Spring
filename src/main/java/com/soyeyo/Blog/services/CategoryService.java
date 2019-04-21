package com.soyeyo.Blog.services;

import com.soyeyo.Blog.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    public Iterable<Category> formatAll(Page<Category> all) {
         all.get().forEach(cat->{
             cat.setPosts(new ArrayList<>());
         });

         return all;
    }
}
