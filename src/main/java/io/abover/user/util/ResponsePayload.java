package io.abover.user.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

public class ResponsePayload<T extends ResponsePayloadData> {
    @Getter
    private final boolean success;

    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final LocalDateTime timestamp;

    @Getter
    private Optional<T> data;

    public ResponsePayload() {
        this.success = true;
        this.timestamp = LocalDateTime.now();
    }

    public ResponsePayload(String message) {
        this();
        this.data = Optional.of((T) new ResponsePayloadData(message));
    }

    public ResponsePayload(T data) {
        this();

        if (data != null) {
            this.data = Optional.of(data);
        }
    }
}
