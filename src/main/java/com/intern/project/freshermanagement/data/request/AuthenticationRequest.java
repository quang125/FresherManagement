package com.intern.project.freshermanagement.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
    private String googleAuthenticatorCode;
}
