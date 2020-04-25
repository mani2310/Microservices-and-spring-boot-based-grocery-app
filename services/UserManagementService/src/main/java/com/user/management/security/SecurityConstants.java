package com.user.management.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/signup";
    public static final String  SIGN_IN = "/user/signin";
    public static final String AUTH="auth";
    public static final String AUTHORIZATION="Authorization";
    public static final long validityInMilliseconds = 3600000;
}
