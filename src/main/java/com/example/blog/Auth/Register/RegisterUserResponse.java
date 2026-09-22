package com.example.blog.Auth.Register;

public record RegisterUserResponse(
                String message,
                String token,
                String refreshToken) {

}
