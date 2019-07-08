package com.soyeyo.Blog.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUpdateException extends RuntimeException {

    public InvalidUpdateException(String ex){
       super(ex);
    }
}
