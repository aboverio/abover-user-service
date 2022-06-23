package io.abover.user.validation;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserEmailSchema {
    @NotNull(message = "Email cannot be empty")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot start with space")
    @Email(message = "Email is in invalid format")
    private String email;
}
