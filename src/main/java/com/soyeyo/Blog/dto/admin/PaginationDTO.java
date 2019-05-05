package com.soyeyo.Blog.dto.admin;

public class PaginationDTO<T>{
    private Link links;
    private Iterable<T> data;


    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public Iterable<T> getData() {
        return data;
    }

    public void setData(Iterable<T> data) {
        this.data = data;
    }


}
