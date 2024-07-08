package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.request.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    String authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest);
}
