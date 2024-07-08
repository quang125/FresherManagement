package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class UserRegisterInvalidException extends BusinessException {
    public UserRegisterInvalidException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "User is invalid !";
    }
}