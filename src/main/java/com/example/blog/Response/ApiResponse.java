package com.example.blog.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ApiResponse
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse(
                String message,
                Object data) {
}