package com.example.blog.Blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {
    @NotBlank
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank
    @Size(min = 2, max = 5000)
    private String body;

    @NotBlank
    private Long userId;
}
