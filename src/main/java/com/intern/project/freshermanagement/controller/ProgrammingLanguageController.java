package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.LanguageDTO;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class ProgrammingLanguageController {
    private final ProgrammingLanguageService programmingLanguageService;
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> findByName(@RequestParam("name") String name){
        return ResponseEntity.ok(new ApiResponse(200, "search language success", programmingLanguageService.findByName(name)));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addLanguage(@RequestBody LanguageDTO commandLanguageDTO){
        return ResponseEntity.ok(new ApiResponse(200, "create language success", programmingLanguageService.create(commandLanguageDTO)));
    }
    @GetMapping("/get")
    public ResponseEntity<ApiResponse> findAll(){
        return ResponseEntity.ok(new ApiResponse(200, "get language success", programmingLanguageService.findAll()));
    }
    @GetMapping("/find")
    public ResponseEntity<ApiResponse> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(new ApiResponse(200, "get language success", programmingLanguageService.findById(id)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam("id") Long id){
        programmingLanguageService.delete(id);
        return ResponseEntity.ok(new ApiResponse(200, "delete language success"));
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateLanguage(@RequestBody LanguageDTO languageDTO, @RequestParam("id") Long id){
        return ResponseEntity.ok(new ApiResponse(200, "update language success", programmingLanguageService.update(languageDTO, id)));
    }
}
