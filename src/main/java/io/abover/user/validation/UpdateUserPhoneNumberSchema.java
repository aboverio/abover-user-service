package io.abover.user.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserPhoneNumberSchema {
    @NotNull(message = "Phone Number cannot be empty")
    @NotEmpty(message = "Phone Number cannot be empty")
    @NotBlank(message = "Phone Number cannot start with space")
    private String phoneNumber;
}
