package com.array.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author XIII
 */
public interface StringEnum {

    @JsonValue
    String getValue();
}
