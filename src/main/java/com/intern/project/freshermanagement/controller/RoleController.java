package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll() {
        return ResponseEntity.ok(new ApiResponse(200, "Find all roles success", roleRepository.findAll()));
    }

}
