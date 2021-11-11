package com.array.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author XIII
 */
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "An email format is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
