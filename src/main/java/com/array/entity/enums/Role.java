package com.array.entity.enums;

import com.array.entity.helpers.EntityConstants;

/**
 * @author XIII
 */
public enum Role implements StringEnum {

    ADMIN(EntityConstants.Role.ADMIN), CUSTOMER(EntityConstants.Role.CUSTOMER);

    String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }
}
