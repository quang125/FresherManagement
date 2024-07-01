package com.intern.project.freshermanagement.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private int code;
    private String message;
    private Object results;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, Object results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }
}
