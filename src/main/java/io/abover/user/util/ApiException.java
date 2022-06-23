package io.abover.user.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"cause", "stack_trace", "message", "suppressed", "localized_message"})
public class ApiException extends RuntimeException {
    @Getter
    @JsonIgnore
    private final HttpStatus code;

    @Getter
    private final boolean success;

    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final LocalDateTime timestamp;

    @Getter
    private ApiExceptionData data;

    public ApiException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApiException(HttpStatus code) {
        super();

        this.code = code;
        this.success = false;
        this.timestamp = LocalDateTime.now();
        this.data = new ApiExceptionData(code.getReasonPhrase());
    }

    public ApiException(HttpStatus code, String message) {
        this(code);
        this.data = new ApiExceptionData(message);
    }

    public <T extends ApiExceptionData> ApiException(HttpStatus code, @NotNull T data) {
        this(code);
        this.data = data;
    }
}
