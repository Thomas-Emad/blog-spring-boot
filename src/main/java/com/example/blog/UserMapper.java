package com.example.blog;

import org.springframework.stereotype.Component;

import com.example.blog.User.User;
import com.example.blog.User.UserResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
