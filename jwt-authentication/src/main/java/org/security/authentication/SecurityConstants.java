package org.security.authentication;

import org.security.authentication.util.RandomKeyGenerator;

public class SecurityConstants {
    public static final String REGISTER_PATH = "/users/register";
    public static final String LOGIN_PATH = "/users/login";
    public static final String SECRET_KEY = RandomKeyGenerator.generateRandomKey();
    public static final int TOKEN_EXPIRATION = 7200000;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
}
