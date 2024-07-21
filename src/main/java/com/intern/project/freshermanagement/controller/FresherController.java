package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CreateFresherRequest;
import com.intern.project.freshermanagement.data.request.UpdateFresherRequest;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.FresherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fresher")
public class FresherController {
    private final FresherService fresherService;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse>findAll(Pageable pageable){
        return ResponseEntity.ok(new ApiResponse(200,"find all fresher success!",fresherService.findAll(pageable)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse>findById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse(200,"find all fresher success!",fresherService.findById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse>create(@RequestBody CreateFresherRequest createFresherDTO,
                                             @RequestParam("groupId") Long groupId){
        return ResponseEntity.ok(new ApiResponse(200,"create fresher success!",fresherService.createFresher(createFresherDTO, groupId)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse>update(@RequestBody UpdateFresherRequest updateFresherRequest,
                                             @PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse(200,"update fresher success!",fresherService.updateFresher(updateFresherRequest, id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Long id){
        fresherService.deleteFresher(id);
        return ResponseEntity.ok(new ApiResponse(200, "delete fresher success"));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse>findByParams(@RequestParam(name="groupId", required = false) Long groupId, @RequestParam(name="languageId", required = false) Long languageId,
                                                   @RequestParam(name="name", required = false) String name, @RequestParam(name="email", required = false) String email,
                                                   Pageable pageable){
        return ResponseEntity.ok(new ApiResponse(200, "search fresher success",
                fresherService.findByParams(groupId, languageId, name, email, pageable)));
    }
    @PutMapping("/update/score/{id}")
    public ResponseEntity<ApiResponse>updateScore(@RequestBody UpdateFresherRequest updateFresherRequest,
                                                  @PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse(200,"update fresher success!",fresherService.updateFresher(updateFresherRequest, id)));
    }
}
