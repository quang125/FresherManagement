package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CommandLanguageDTO;
import com.intern.project.freshermanagement.data.request.CommandProjectDTO;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.InternshipProjectService;
import com.intern.project.freshermanagement.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class InternshipProjectController {
    private final InternshipProjectService internshipProjectService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addProject(@RequestBody CommandProjectDTO commandProjectDTO){
        return ResponseEntity.ok(new ApiResponse(200, "create project success", internshipProjectService.create(commandProjectDTO)));
    }
    @GetMapping("/get")
    public ResponseEntity<ApiResponse> findAll(){
        return ResponseEntity.ok(new ApiResponse(200, "get projects success", internshipProjectService.findAll()));
    }
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse> findByName(@RequestParam("name") String name){
        return ResponseEntity.ok(new ApiResponse(200, "search projects success", internshipProjectService.findByName(name)));
    }
    @GetMapping("/search/language")
    public ResponseEntity<ApiResponse> findByLanguage(@RequestParam("name") String name){
        return ResponseEntity.ok(new ApiResponse(200, "search projects success", internshipProjectService.findByLanguageName(name)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam("id") Long id){
        internshipProjectService.delete(id);
        return ResponseEntity.ok(new ApiResponse(200, "delete project success"));
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateProject(@RequestBody CommandProjectDTO commandProjectDTO){
        return ResponseEntity.ok(new ApiResponse(200, "update project success", internshipProjectService.update(commandProjectDTO)));
    }
}
