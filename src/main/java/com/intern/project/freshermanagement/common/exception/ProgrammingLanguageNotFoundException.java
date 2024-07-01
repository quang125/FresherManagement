package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class ProgrammingLanguageNotFoundException extends BusinessException{
    public ProgrammingLanguageNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Programming language not found";
    }
}
