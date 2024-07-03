package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class FresherNotFoundException extends BusinessException{
    public FresherNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Fresher not found";
    }
}
