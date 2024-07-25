package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class InternshipGroupController {
    private final InternshipGroupService internshipGroupService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse>createInternshipGroupManual(@RequestBody CreateInternshipGroupRequest request, @RequestParam("officeId") Long id){
        return ResponseEntity.ok(new ApiResponse(200,"create internship project success", internshipGroupService.createGroup(request, id)));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse>findAll(Pageable pageable){
        return ResponseEntity.ok(new ApiResponse(200,"find internship projects success", internshipGroupService.findAll(pageable)));
    }
    @GetMapping("/by-school")
    public ResponseEntity<ApiResponse>findBySchool(@RequestParam("schoolId") Long schoolId, Pageable pageable){
        return ResponseEntity.ok(new ApiResponse(200,"find internship projects success", internshipGroupService.findBySchool(schoolId, true, pageable)));
    }
    @GetMapping("/by-office")
    public ResponseEntity<ApiResponse>findByOffice(@RequestParam("officeId") Long officeId, Pageable pageable){
        return ResponseEntity.ok(new ApiResponse(200,"find internship projects success", internshipGroupService.findBySchool(officeId, true, pageable)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse>findById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse(200,"find internship project success", internshipGroupService.findById(id, true)));
    }
}
