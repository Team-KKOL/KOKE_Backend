package com.koke.koke_backend.common.dto;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public record ApiResponse<T>(String message, T body) {

    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String CACHING_SUCCESS_MESSAGE = "CACHING SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";

    public static <T> ResponseEntity<ApiResponse<T>> success() {
        ApiResponse<T> apiResponse = new ApiResponse<>(SUCCESS_MESSAGE, null);
        return ResponseEntity.ok(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, null);
        return ResponseEntity.ok(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(SUCCESS_MESSAGE, body);
        return ResponseEntity.ok(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> cachingSuccess(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(CACHING_SUCCESS_MESSAGE, body);
        return ResponseEntity.ok(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(FAILED_MESSAGE, body);
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, body);
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, null);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, body);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, null);
        return ResponseEntity.status(FORBIDDEN).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, null);
        return ResponseEntity.status(UNAUTHORIZED).body(apiResponse);
    }
}
