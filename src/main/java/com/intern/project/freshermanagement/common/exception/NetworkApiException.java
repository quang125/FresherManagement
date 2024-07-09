package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class NetworkApiException extends BusinessException {
    public NetworkApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
