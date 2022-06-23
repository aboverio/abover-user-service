package io.abover.user.util;

import lombok.Data;

@Data
public class ResponsePayloadData {
    private String message;

    public ResponsePayloadData(String message) {
        this.message = message;
    }
}
