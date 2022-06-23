package io.abover.user.util;

import lombok.Data;

@Data
public class ApiExceptionData {
    private String message;

    public ApiExceptionData(String message) {
        this.message = message;
    }
}
