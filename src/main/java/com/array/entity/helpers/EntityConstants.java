package com.array.entity.helpers;

/**
 * @author XIII
 */
public abstract class EntityConstants {

    public abstract static class Role {
        public static final String ADMIN = "ADMIN";
        public static final String CUSTOMER = "CUSTOMER";
    }

    public abstract static class BaseEntity {
        public static final String CREATED_BY = "created_by";
        public static final String UPDATED_BY = "updated_by";
    }

    public abstract static class User {
        public static final String TABLE_NAME = "users";
    }

    public abstract static class UserRole {
        public static final String TABLE_NAME = "user_roles";
    }
}
