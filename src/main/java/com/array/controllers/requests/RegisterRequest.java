package com.array.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author XIII
 */
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @Email(message = "An email format is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
