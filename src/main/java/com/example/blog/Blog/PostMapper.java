package com.example.blog.Blog;

import org.springframework.stereotype.Component;

import com.example.blog.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;

    public PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                userMapper.toResponse(post.getUser()));
    }
}
