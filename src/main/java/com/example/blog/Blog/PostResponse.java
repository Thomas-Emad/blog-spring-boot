package com.example.blog.Blog;

import com.example.blog.User.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private UserResponse user;
}