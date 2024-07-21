package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.request.CreateOfficeRequest;
import com.intern.project.freshermanagement.data.request.UpdateOfficeRequest;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/office")
public class OfficeController {
    private final OfficeService officeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOffice() {
        return ResponseEntity.ok(new ApiResponse(200, "Get all offices success", officeService.findAll(true)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(200, "Get all offices success", officeService.findById(id, true)));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateOfficeRequest createOfficeRequest) {
        return ResponseEntity.ok(new ApiResponse(200, "Create office success", officeService.create(createOfficeRequest)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateOfficeRequest updateOfficeRequest,
                                              @PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(200, "Update office success", officeService.update(updateOfficeRequest, id)));
    }
    @DeleteMapping("/delte/{id}")
    public ResponseEntity<ApiResponse> delete(@RequestBody UpdateOfficeRequest updateOfficeRequest,
                                              @PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(200, "Update office success", officeService.update(updateOfficeRequest, id)));
    }
}
