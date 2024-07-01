package com.intern.project.freshermanagement.common.constants;

/**
 * @author QuangNN
 */
public class JwtConstant {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String JWT_SECRET = "Fresher-management";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

}
