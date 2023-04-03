package com.koke.koke_backend.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public record ResponseMapper<T>(String message, @JsonInclude(JsonInclude.Include.NON_NULL) T body) {

    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String CACHING_SUCCESS_MESSAGE = "CACHING SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";

    public static <T> ResponseEntity<ResponseMapper<T>> success() {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(SUCCESS_MESSAGE, null);
        return ResponseEntity.ok(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> success(String message) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, null);
        return ResponseEntity.ok(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> success(T body) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(SUCCESS_MESSAGE, body);
        return ResponseEntity.ok(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> cachingSuccess(T body) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(CACHING_SUCCESS_MESSAGE, body);
        return ResponseEntity.ok(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> fail(T body) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(FAILED_MESSAGE, body);
        return ResponseEntity.internalServerError().body(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> fail(String message, T body) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, body);
        return ResponseEntity.internalServerError().body(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> badRequest(String message) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, null);
        return ResponseEntity.badRequest().body(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> badRequest(String message, T body) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, body);
        return ResponseEntity.badRequest().body(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> forbidden(String message) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, null);
        return ResponseEntity.status(FORBIDDEN).body(responseMapper);
    }

    public static <T> ResponseEntity<ResponseMapper<T>> unauthorized(String message) {
        ResponseMapper<T> responseMapper = new ResponseMapper<>(message, null);
        return ResponseEntity.status(UNAUTHORIZED).body(responseMapper);
    }

}
