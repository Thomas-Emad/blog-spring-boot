package com.example.blog.Exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String messgae) {
        super(messgae);
    }
}
