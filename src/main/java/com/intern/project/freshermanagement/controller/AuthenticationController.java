package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.AuthenticationRequest;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(HttpServletRequest httpServletRequest, @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new ApiResponse(200, "Authenticate success", authenticationService.authenticate(request, httpServletRequest)));
    }

}