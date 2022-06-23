package io.abover.user.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserSchema {
    @NotNull(message = "First Name cannot be empty")
    @NotEmpty(message = "First Name cannot be empty")
    @NotBlank(message = "First Name cannot start with space")
    private String firstName;

    private String lastName;
}
