package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CommandSchoolDTO;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/school")
public class SchoolController {
    private final SchoolService schoolService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addSchool(@RequestBody CommandSchoolDTO school){
        return ResponseEntity.ok(new ApiResponse(200, "create school success", schoolService.create(school)));
    }
    @GetMapping("/get")
    public ResponseEntity<ApiResponse> findAll(){
        return ResponseEntity.ok(new ApiResponse(200, "get school success", schoolService.findAll()));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> findByName(@RequestParam("name") String name){
        return ResponseEntity.ok(new ApiResponse(200, "search school success", schoolService.findByName(name)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam("id") Long id){
        schoolService.deleteSchool(id);
        return ResponseEntity.ok(new ApiResponse(200, "delete school success"));
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateSchool(@RequestBody CommandSchoolDTO school, @RequestParam("id") Long id){
        return ResponseEntity.ok(new ApiResponse(200, "update school success", schoolService.update(school, id)));
    }
}
