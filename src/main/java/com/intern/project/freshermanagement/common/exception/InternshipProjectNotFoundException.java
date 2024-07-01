package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class InternshipProjectNotFoundException extends BusinessException{
    public InternshipProjectNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Internship project not found";
    }
}
