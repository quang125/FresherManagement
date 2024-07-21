package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.ActiveUserRequest;
import com.intern.project.freshermanagement.data.request.ChangePasswordRequest;
import com.intern.project.freshermanagement.data.request.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(Long id);

    List<User> findAll();

    User create(User user);

    void save(User user);

    void resetPassword(String email);

    User changePassword(ChangePasswordRequest changePasswordRequest);

    User update(UserDTO userDTO);

    User updateUserRole(User user);

    void deactivate(String id);

    User activeUser(ActiveUserRequest activeUserRequest);

    void resendQRCode(String email);
}
