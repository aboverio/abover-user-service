package io.abover.user.validation;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserPasswordSchema {
    @NotNull(message = "Password cannot be empty")
    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot start with space")
    @Min(value = 6, message = "Password must be at least 6 characters")
    private String password;
}
