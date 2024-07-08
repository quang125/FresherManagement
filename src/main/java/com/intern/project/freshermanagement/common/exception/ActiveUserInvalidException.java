package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class ActiveUserInvalidException extends BusinessException {
    public ActiveUserInvalidException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Invalid information confirm register";
    }
}
