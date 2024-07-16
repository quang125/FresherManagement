package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.*;
import com.intern.project.freshermanagement.common.util.GoogleAuthenticatorUtils;
import com.intern.project.freshermanagement.common.util.Validator;
import com.intern.project.freshermanagement.data.MyUserDetails;
import com.intern.project.freshermanagement.data.entity.Role;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.ActiveUserRequest;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,20}$";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException(email);
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
    public User activeUser(ActiveUserRequest activeUserRequest) {
        String email = activeUserRequest.getEmail();
        if (StringUtils.isBlank(email)) {
            throw new ActiveUserInvalidException();
        }
        User user = userRepository.findInactiveUserByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException(email);
        });
        user.setActive(true);
        user.setFailedAttempt(0);
        String googleAuthenticatorCode = activeUserRequest.getGoogleAuthenticatorCode();
        String currentCode = GoogleAuthenticatorUtils.getTOTPCode(user.getGoogleAuthenticatorSecretKey());
        if (StringUtils.isEmpty(googleAuthenticatorCode) || !googleAuthenticatorCode.equals(currentCode)) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Google Authenticator code is invalid")
                    .build();
        }
        String password = activeUserRequest.getPassword();
        if (!password.matches(PASSWORD_PATTERN)) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Password must contains at least one digit, uppercase char and symbol.")
                    .build();
        }
        String name = activeUserRequest.getName();
        if (StringUtils.isBlank(password) || StringUtils.isBlank(name)) {
            throw new ActiveUserInvalidException();
        }
        user.setFullName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public void resendQRCode(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException(email);
        });
        if (StringUtils.isNotBlank(user.getGoogleAuthenticatorSecretKey())) {
            mailService.sendQRCode(email, user.getGoogleAuthenticatorSecretKey());
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        String email = user.getEmail();
        if (!Validator.isValidEmail(email) || user.getRole() == null) {
            throw new UserRegisterInvalidException();
        }
        User existedUser = userRepository.findByEmail(email).orElse(null);
        if (existedUser != null) {
            throw new UserExistedException(email);
        }
        user.setGoogleAuthenticatorSecretKey(GoogleAuthenticatorUtils.generateSecretKey());
        String newPassword=RandomStringUtils.randomAlphabetic(10);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        mailService.sendActiveUserMail(email, user.getGoogleAuthenticatorSecretKey(), newPassword);
        return userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
