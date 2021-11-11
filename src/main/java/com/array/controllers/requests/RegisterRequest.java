package com.array.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @NotBlank(message = "Email is required")
    @Email(message = "An email format is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,}$",
            message="Password must contain a length of at least 3 characters included at least one digit [0-9]," +
                    " one lowercase [a-z], one uppercase [A-Z].")
    private String password;
}
