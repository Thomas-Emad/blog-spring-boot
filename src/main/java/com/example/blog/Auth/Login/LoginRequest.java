package com.example.blog.Auth.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter 
public class LoginRequest {

    @NotBlank
    public String email;

    @NotBlank
    public String password;
}
