package com.user.acquisition.common.constants;

/**
 * @author TruongNH
 */
public class JwtConstant {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String JWT_SECRET = "Falcon";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

}
