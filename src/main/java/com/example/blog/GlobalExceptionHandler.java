package com.example.blog;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blog.Exception.UserAlreadyExistsException;
import com.example.blog.Response.ApiResponse;
import com.example.blog.Response.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiResponse> handleBadCredentials(BadCredentialsException e) {
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(new ApiResponse(e.getMessage(), null));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> handleValidation(
                        MethodArgumentNotValidException e) {

                Map<String, String> errors = e.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .collect(Collectors.toMap(
                                                error -> Objects.requireNonNullElse(
                                                                error.getField(),
                                                                "Invalid Value"),
                                                error -> Objects.requireNonNullElse(
                                                                error.getDefaultMessage(),
                                                                "Invalid value")));

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new ValidationErrorResponse(
                                                "Validation failed",
                                                errors));
        }

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ApiResponse> handleUserAlreadyExists(
                        UserAlreadyExistsException e) {

                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(new ApiResponse(e.getMessage(), null));
        }
}
