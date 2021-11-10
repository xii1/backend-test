package com.array.controllers.helpers;

/**
 * @author XIII
 */
public abstract class RestConstants {

    public abstract static class Authentication {
        public static final String PREFIX = "/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }

    public abstract static class Authorization {
        public static final String HAS_AUTHORITY_ADMIN = "hasAnyAuthority('ADMIN')";
        public static final String HAS_ANY_AUTHORITY_ADMIN_CUSTOMER = "hasAnyAuthority('ADMIN','CUSTOMER')";
    }
}
