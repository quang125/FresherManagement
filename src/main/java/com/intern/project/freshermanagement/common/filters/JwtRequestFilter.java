package com.intern.project.freshermanagement.common.filters;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.intern.project.freshermanagement.common.constants.JwtConstant;
import com.intern.project.freshermanagement.common.exception.AuthorizationException;
import com.intern.project.freshermanagement.common.util.JwtUtils;
import com.intern.project.freshermanagement.data.MyUserDetails;
import com.intern.project.freshermanagement.data.response.ApiResponse;
import com.intern.project.freshermanagement.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TruongNH
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtConstant.JWT_HEADER);
        if (header != null && header.startsWith(JwtConstant.JWT_TOKEN_PREFIX)) {
            String token = header.substring(JwtConstant.JWT_TOKEN_PREFIX.length());
            String username;
            try {
                username = JwtUtils.extractUsername(token);
            } catch (JwtException e) {
                AuthorizationException exception = new AuthorizationException(e.getMessage());
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(mapper.writeValueAsString(new ApiResponse(exception.getStatus().value(), exception.getMessage())));
                return;
            }
            MyUserDetails myUserDetails = (MyUserDetails) userService.loadUserByUsername(username);
            if (JwtUtils.validateToken(token, myUserDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
