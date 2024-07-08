package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class UserExistedException extends BusinessException {
    public UserExistedException(String email) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "User is existed with email: " + email;
    }
}