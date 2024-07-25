package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.FresherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final FresherService fresherService;

    @GetMapping("/count/office")
    public ResponseEntity<ApiResponse> countFresherByOffice(){
        return ResponseEntity.ok(new ApiResponse(200, "success", fresherService.getFresherStatsByOffice()));
    }
    @GetMapping("/count/score")
    public ResponseEntity<ApiResponse> countFresherByScore(){
        return ResponseEntity.ok(new ApiResponse(200, "success", fresherService.getFresherStatsByScore()));
    }
}
