package com.summercampquest.campquest.service;

public class SecurityConstant {

    public static final long EXPIRATION_TIME = 432_000_000; // 5 days in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String JWT_TOKEN_HEADER = "Jwt-Token";

    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot be verified";

    public static final String GET_ARRAYS_LLC = "Summer Camp Quest";

    public static final String GET_ARRAYS_ADMINISTRATION= "Camp Quest Users";//token audience

    public static final String AUTHORITIES = "authorities";

    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";

    public static final String ACCESS_DENIED_MESAGE = "You do not have permission to access this page";

    public static final String OPTIONS_HTTP_METHOD = "OPTIONS"; // Allow options request


    public static final String[] PUBLIC_URLS = { "/api/camps/**", "/auth/login","/api/create-camp/**","/auth/register"};

    /***********************/
    // Testing application with disabled security
            //TODO: comment out
//    public static final String[] PUBLIC_URLS = {"**"};
    /********************/

}


















