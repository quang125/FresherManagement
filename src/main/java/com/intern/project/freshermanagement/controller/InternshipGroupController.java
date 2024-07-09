package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class InternshipGroupController {
    private final InternshipGroupService internshipGroupService;
    @PostMapping("/create/file")
    public ResponseEntity<ApiResponse>createInternshipGroupWithFile(@RequestParam MultipartFile file){
        return ResponseEntity.ok(new ApiResponse(200,"create success", internshipGroupService.createGroup(file)));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse>createInternshipGroupManual(@RequestBody CreateInternshipGroupRequest request){
        return ResponseEntity.ok(new ApiResponse(200,"create success", internshipGroupService.createGroup(request)));
    }
}
