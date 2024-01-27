package com.bobo.resto.shared.util;

public class Constant {
    public static final String BASE_API = "/api/v1";

    public static final String WAITER = "ROLE_WAITER";
    public static final String COOK = "ROLE_COOK";
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String SYS_ADMIN = "ROLE_SYS_ADMIN";

    public static final String READ = "READ";
    public static final String WRITE = "WRITE";
    public static final String DELETE = "DELETE";

    public static final int ACCESS_TOKEN_DURATION =  60 * 60 * 1000;
    public static final int REFRESH_TOKEN_DURATION = 60 * 1440 * 1000;

    public static final int RESET_PASSWORD_CODE_VALIDITY = 3;
    public static final int RESET_PASSWORD_CODE_LENGTH = 6;

    public static final String RESET_PASSWORD_TEMPLATE_NAME = "ResetPassword";
}