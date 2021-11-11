package com.array.entity.helpers;

/**
 * @author XIII
 */
public abstract class EntityConstants {

    public abstract static class Role {
        public static final String ADMIN = "ADMIN";
        public static final String CUSTOMER = "CUSTOMER";
    }

    public abstract static class User {
        public static final String TABLE_NAME = "users";
    }

    public abstract static class UserRole {
        public static final String TABLE_NAME = "user_roles";
    }
}
