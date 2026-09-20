package com.example.blog.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    public UserResponse(Long id, String name, String email) {
        this.id = id;
        this.username = name;
        this.email = email;
    }

    private Long id;
    private String username;
    private String email;
    private String password;
}
