package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.service.InternshipGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class InternshipGroupController {
    private final InternshipGroupService internshipGroupService;
    @PostMapping("/create")
    public ResponseEntity<?>createInternshipGroup(@RequestParam MultipartFile file){
        return ResponseEntity.ok(internshipGroupService.createGroup(file));
    }
}
