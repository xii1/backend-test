package com.array.controllers.helpers;

/**
 * @author XIII
 */
public abstract class PathConstants {

    public abstract static class Authentication {
        public static final String PREFIX = "/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }

    public abstract static class Authorization {
        public static final String HAS_ANY_AUTHORITY_ADMIN_PROVIDER_CLIENT = "hasAnyAuthority('ADMIN','PROVIDER', 'CLIENT')";
        public static final String HAS_ANY_AUTHORITY_ADMIN_CLIENT = "hasAnyAuthority('ADMIN', 'CLIENT')";
        public static final String HAS_ANY_AUTHORITY_ADMIN_PROVIDER = "hasAnyAuthority('ADMIN','PROVIDER')";
        public static final String HAS_AUTHORITY_ADMIN = "hasAnyAuthority('ADMIN')";

    }
}
