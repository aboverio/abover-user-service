package io.abover.user.controller;

import io.abover.user.dto.UserDto;
import io.abover.user.model.User;
import io.abover.user.service.UserService;
import io.abover.user.util.ApiException;
import io.abover.user.util.ResponsePayload;
import io.abover.user.validation.CreateUserSchema;
import io.abover.user.validation.UpdateUserPhoneNumberSchema;
import io.abover.user.validation.UpdateUserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePayload<UserDto>> create(@Valid @RequestBody CreateUserSchema createUserData) throws ApiException {
        UserDto createdUser = this.service.create(createUserData);

        ResponsePayload<UserDto> res = new ResponsePayload<>(createdUser);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePayload<UserDto>> findById(@RequestAttribute User user) throws ApiException {
        ResponsePayload<UserDto> res = new ResponsePayload<>(new UserDto("Successfully retrieved user", user));

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePayload<UserDto>> update(@RequestAttribute User user, @Valid @RequestBody UpdateUserSchema updateData) {
        UserDto updatedUser = this.service.update(user, updateData);

        ResponsePayload<UserDto> res = new ResponsePayload<>(updatedUser);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/phone-number")
    public ResponseEntity<ResponsePayload<UserDto>> updatePhoneNumber(@RequestAttribute User user, @Valid @RequestBody UpdateUserPhoneNumberSchema updateUserPhoneNumberData) {
        UserDto updatedUser = this.service.updatePhoneNumber(user, updateUserPhoneNumberData);

        ResponsePayload<UserDto> res = new ResponsePayload<>(updatedUser);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiException> handleException(ApiException err) {
        return new ResponseEntity<>(err, err.getCode());
    }
}
