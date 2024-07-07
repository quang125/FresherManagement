package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.ChangePasswordRequest;
import com.intern.project.freshermanagement.data.request.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * Find all users in the system
     *
     * @return all users
     */
    List<User> findAll();
    User create(User user);
    /**
     * Reset password
     *
     * @param email email to reset
     */
    void resetPassword(String email);

    /**
     * Change password of user
     *
     * @param changePasswordRequest contains password information
     *
     * @return user with new password
     */
    User changePassword(ChangePasswordRequest changePasswordRequest);
    /**
     * Update name, phone of user
     *
     * @param userDTO contains id, phone and name
     *
     * @return updated User
     */
    User update(UserDTO userDTO);

    /**
     * Update the role, appIds of user
     *
     * @param user contains the role, appIds and id of User
     *
     * @return updated User
     */
    User updateUserRole(User user);

    /**
     * Deactivate a user
     *
     * @param id id of user
     */
    void deactivate(String id);

}
