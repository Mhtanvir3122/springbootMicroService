package com.example.AuthService.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
@Setter
@Getter
public class ApiResponse<T> {
    private long timestamp;
    private int status;
    private String message;
    private T body;
    private Object fieldErrors;
    private Object error;
    private Object header;
    private Object meta;

    public ApiResponse(int status, String message, T body) {
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public ApiResponse() {

    }

    // getters and setters
}
