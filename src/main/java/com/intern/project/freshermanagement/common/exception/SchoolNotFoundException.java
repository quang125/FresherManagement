package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class SchoolNotFoundException extends BusinessException{
    public SchoolNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "School not found";
    }
}
