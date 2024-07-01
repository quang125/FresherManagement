package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class InternshipGroupNotFoundException extends BusinessException{
    public InternshipGroupNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Internship group not found";
    }
}
