package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.data.MyUserDetails;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.ActiveUserRequest;
import com.intern.project.freshermanagement.data.request.ChangePasswordRequest;
import com.intern.project.freshermanagement.data.request.UserDTO;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getCurrentUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new ApiResponse(200, "Get current user success", myUserDetails.getUser()));
    }
    @GetMapping("/user/all")
    public ResponseEntity<ApiResponse> getAllUser() {
        return ResponseEntity.ok(new ApiResponse(200, "Get all users success", userService.findAll()));
    }
    @PostMapping("/user")
    public ResponseEntity<ApiResponse> create(@RequestBody User user) {
        return ResponseEntity.ok(new ApiResponse(200, "Create non-active user success", userService.create(user)));
    }
    @PostMapping("/user/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok(new ApiResponse(200, "Request to reset password success, please check your email"));
    }
    @PutMapping("/user/change-password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(new ApiResponse(200, "Change user password success", userService.changePassword(changePasswordRequest)));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> update(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponse(200, "Update user success", userService.update(userDTO)));
    }
    @PutMapping("/user/role")
    public ResponseEntity<ApiResponse> updateUserRole(@RequestBody User user) {
        return ResponseEntity.ok(new ApiResponse(200, "Update user success", userService.updateUserRole(user)));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deactivate(@PathVariable String id) {
        userService.deactivate(id);
        return ResponseEntity.ok(new ApiResponse(200, "Deactivate user success"));
    }

    @PostMapping("/user/resend/qr-code")
    public ResponseEntity<ApiResponse> sendQRCode(@RequestParam("email") String email) {
        userService.resendQRCode(email);
        return ResponseEntity.ok(new ApiResponse(200, "Request to reset QR success, please check your email"));
    }

    @PostMapping("/user/active")
    public ResponseEntity<ApiResponse> activeUser(@RequestBody ActiveUserRequest activeUserRequest) {
        return ResponseEntity.ok(new ApiResponse(200, "Confirm active user success", userService.activeUser(activeUserRequest)));
    }
}
