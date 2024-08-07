package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.ProjectDTO;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.InternshipProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class InternshipProjectController {
    private final InternshipProjectService internshipProjectService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody ProjectDTO commandProjectDTO){
        return ResponseEntity.ok(new ApiResponse(200, "create project success", internshipProjectService.create(commandProjectDTO)));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(){
        return ResponseEntity.ok(new ApiResponse(200, "get projects success", internshipProjectService.findAll(true)));
    }
    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse> findByName(@RequestParam("name") String name){
        return ResponseEntity.ok(new ApiResponse(200, "search projects success", internshipProjectService.findByName(name, true)));
    }
    @GetMapping("/by-language")
    public ResponseEntity<ApiResponse> findByLanguage(@RequestParam("languageId") Long id){
        return ResponseEntity.ok(new ApiResponse(200, "search projects success", internshipProjectService.findByLanguage(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        internshipProjectService.delete(id);
        return ResponseEntity.ok(new ApiResponse(200, "delete project success"));
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody ProjectDTO commandProjectDTO, @RequestParam("id") Long id){
        return ResponseEntity.ok(new ApiResponse(200, "update project success", internshipProjectService.update(commandProjectDTO, id)));
    }
}
