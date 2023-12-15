package com.artmuh.taskmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyEntityNotFoundException extends RuntimeException{

    public MyEntityNotFoundException(Long id){
        super("User is not found , id="+id);
    }
}
