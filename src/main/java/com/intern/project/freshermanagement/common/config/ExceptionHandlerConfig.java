package com.intern.project.freshermanagement.common.config;

import com.intern.project.freshermanagement.common.exception.BusinessException;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse> apiException(BusinessException e) {
        int code = e.getStatus() != null ? e.getStatus().value() : 11111;
        e.printStackTrace();
        return ResponseEntity.ok(new ApiResponse(code, e.getMessage()));
    }
}
