package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.BusinessException;
import com.intern.project.freshermanagement.common.exception.UserNotFoundException;
import com.intern.project.freshermanagement.common.util.Validator;
import com.intern.project.freshermanagement.data.MyUserDetails;
import com.intern.project.freshermanagement.data.entity.Role;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.ChangePasswordRequest;
import com.intern.project.freshermanagement.data.request.UserDTO;
import com.intern.project.freshermanagement.repository.UserRepository;
import com.intern.project.freshermanagement.service.MailService;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,20}$";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> {
            throw new UserNotFoundException(username);
        });
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new MyUserDetails(user.getEmail(), user.getPassword(), true, true, true, true, authorities, user);

    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException(email);
        });

        String newPassword = RandomStringUtils.randomAlphabetic(10);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
        mailService.sendResetPassword(email, newPassword);
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        String currentPassword = changePasswordRequest.getCurrentPassword();
        String newPassword = changePasswordRequest.getNewPassword();
        if (StringUtils.isBlank(currentPassword) || StringUtils.isBlank(newPassword)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Information to change password is invalid");
        }

        if (!newPassword.matches(PASSWORD_PATTERN)) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Password must contains at least one digit, uppercase char and symbol")
                    .build();
        }

        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = myUserDetails.getUser();
        if (!bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
            throw BusinessException.builder().status(HttpStatus.BAD_REQUEST).message("Current password don't match").build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    public User update(UserDTO userDTO) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = myUserDetails.getUser();
        String name = userDTO.getName();
        String phone = userDTO.getPhone();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(name)) {
            user.setFullName(name);
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(phone)) {
            if (!Validator.isValidPhone(phone)) {
                throw BusinessException.builder().status(HttpStatus.BAD_REQUEST).message("Phone number is invalid").build();
            }
            user.setPhoneNumber(phone);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserRole(User user) {
        Role role = user.getRole();
        if (role == null) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Information update user role invalid")
                    .build();
        }
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        currentUser.setRole(role);
        return userRepository.save(currentUser);
    }

    @Override
    public void deactivate(String id) {

    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

}
