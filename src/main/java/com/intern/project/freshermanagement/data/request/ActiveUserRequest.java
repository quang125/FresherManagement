package com.intern.project.freshermanagement.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActiveUserRequest {
    private String email;
    private String name;
    private String password;
    private String googleAuthenticatorCode;
}
