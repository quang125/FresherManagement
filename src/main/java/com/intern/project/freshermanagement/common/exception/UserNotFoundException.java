package com.intern.project.freshermanagement.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(String userName){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "User not found: "+userName;
    }
    public UserNotFoundException(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "User not found";
    }
}
