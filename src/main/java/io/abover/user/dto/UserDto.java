package io.abover.user.dto;

import io.abover.user.model.User;
import io.abover.user.util.ResponsePayloadData;
import lombok.Getter;

public class UserDto extends ResponsePayloadData {
    @Getter
    private final User user;

    public UserDto(String message, User user) {
        super(message);
        this.user = user;
    }
}
