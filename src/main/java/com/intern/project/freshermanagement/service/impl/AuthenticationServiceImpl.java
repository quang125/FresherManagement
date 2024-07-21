package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.BusinessException;
import com.intern.project.freshermanagement.common.exception.LoginFailureException;
import com.intern.project.freshermanagement.common.util.GoogleAuthenticatorUtils;
import com.intern.project.freshermanagement.common.util.IpAddressUtils;
import com.intern.project.freshermanagement.common.util.JwtUtils;
import com.intern.project.freshermanagement.data.MyUserDetails;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.AuthenticationRequest;
import com.intern.project.freshermanagement.service.AuthenticationService;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${internal.ip.list}")
    private Set<String> internalIps;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private static final int FAILURE_LIMIT = 15;

    @Override
    public String authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest) {
        final BusinessException lockedUserException = BusinessException.builder()
            .message("The account is locked, please contact your admin.")
            .status(HttpStatus.BAD_REQUEST)
            .build();

        MyUserDetails myUserDetails = (MyUserDetails) userService.loadUserByUsername(request.getEmail());
        User user = myUserDetails.getUser();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            if (user.getFailedAttempt() > FAILURE_LIMIT) {
                user.setFailedAttempt(0);
                user.setActive(false);
            }
            userService.save(user);
            if (!user.isActive()) {
                throw lockedUserException;
            }
            throw new LoginFailureException();
        }
        if (!user.isActive()) {
            throw lockedUserException;
        }
        final String INTERNAL_IP_PREFIX = "192.168";
        String ip = IpAddressUtils.resolveIp(httpServletRequest);
        log.info("Login from IP: {} with email {}", ip, request.getEmail());
        if (internalIps.contains(ip) || ip.startsWith(INTERNAL_IP_PREFIX)) {
            return JwtUtils.generateToken(myUserDetails);
        }

        String googleAuthenticatorCode = request.getGoogleAuthenticatorCode();
        if (StringUtils.isEmpty(googleAuthenticatorCode)) {
            return "";
        }
        String currentCode = GoogleAuthenticatorUtils.getTOTPCode(user.getGoogleAuthenticatorSecretKey());
        if (!googleAuthenticatorCode.equals(currentCode)) {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            if (user.getFailedAttempt() > FAILURE_LIMIT) {
                user.setActive(false);
            }
            userService.save(user);
            throw BusinessException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Google Authenticator code is invalid")
                .build();
        }
        if (user.getFailedAttempt() > 0) {
            user.setFailedAttempt(0);
            userService.save(user);
        }
        return JwtUtils.generateToken(myUserDetails);
    }

}