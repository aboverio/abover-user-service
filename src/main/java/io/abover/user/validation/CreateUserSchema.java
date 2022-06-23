package io.abover.user.validation;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateUserSchema {
    @NotNull(message = "First Name cannot be empty")
    @NotEmpty(message = "First Name cannot be empty")
    @NotBlank(message = "First Name cannot start with space")
    private String firstName;

    private String lastName;

    private String phoneNumber;

    @NotNull(message = "Email cannot be empty")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot start with space")
    @Email(message = "Email is in invalid format")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
