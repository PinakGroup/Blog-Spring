package com.soyeyo.Blog.services;

import com.soyeyo.Blog.data.PostRepository;
import com.soyeyo.Blog.dto.admin.Link;
import com.soyeyo.Blog.dto.admin.PaginationDTO;
import com.soyeyo.Blog.models.Post;
import com.soyeyo.Blog.models.Tag;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.soyeyo.Blog.services.PaginationService.getData;

public class SearchPaginationService {
    @Value("${url.path}")
    private static String url;

    private SearchPaginationService(){}

    @Value(value = "${url.path}")
    public void setUrl(String newUrl){
        url = newUrl;
    }

    public  static ArrayList<Post> getPagination(String page, PostRepository repo, Tag tag, String key, String type){
        if(url == null){
            new SearchPaginationService();
        }
        //set page number
        int pageNo = Integer.parseInt(page);
        //per page

        ArrayList<Post> posts = new ArrayList<>();
        if(type.equals("search")){
            repo.findAllByTitleContains(key).forEach(posts::add);
        }else {
            repo.findAllByTagsContains(tag).forEach(posts::add);
        }


        int from = 10 * (pageNo-1);
        int to = (pageNo * 10) - 1;
        if(posts.size() == 1 && pageNo == 1)return (ArrayList<Post>)PaginationService.getData(posts);
        if(posts.size() == 0 || from > posts.size() - 1)return new ArrayList<>();
        if(posts.size() - 1 < to)to = posts.size() - 1;

        return  (ArrayList<Post>) PaginationService.getData(posts.subList(from,to));
    }

}
