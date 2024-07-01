package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class MentorNotFoundException extends BusinessException{
    public MentorNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Mentor not found";
    }
}
