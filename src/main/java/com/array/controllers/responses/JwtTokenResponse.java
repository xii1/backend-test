package com.array.controllers.responses;

import com.array.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XIII
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtTokenResponse {

    private String token;
    private User user;
}
