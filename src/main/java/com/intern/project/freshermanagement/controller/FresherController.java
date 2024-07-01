package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CreateFresherDTO;
import com.intern.project.freshermanagement.service.FresherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fresher")
public class FresherController {
    private final FresherService fresherService;
    @PostMapping("/create")
    public ResponseEntity<?>createFresher(@RequestBody CreateFresherDTO createFresherDTO){
        return ResponseEntity.ok(fresherService.createFresher(createFresherDTO));
    }
}
