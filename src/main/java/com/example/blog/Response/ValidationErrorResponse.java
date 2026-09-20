package com.example.blog.Response;

import java.util.Map;

/**
 * ValidationErrorResponse
 */
public record ValidationErrorResponse(
        String message,
        Map<String, String> errors) {

}
