package io.abover.user.service;

import io.abover.user.dto.UserDto;
import io.abover.user.model.User;
import io.abover.user.repository.UserRepository;
import io.abover.user.util.ApiException;
import io.abover.user.validation.CreateUserSchema;
import io.abover.user.validation.UpdateUserPhoneNumberSchema;
import io.abover.user.validation.UpdateUserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserDto findById(UUID userId) throws ApiException {
        try {
            User activeUser = new User();
            activeUser.setId(userId);
            activeUser.setDeletedAt(null);

            Example<User> example = Example.of(activeUser);

            Optional<User> foundUser = this.repository.findOne(example);

            if (foundUser.isPresent()) {
                return new UserDto("Successfully retrieved user", foundUser.get());
            }

            throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
        } catch (ApiException err) {
            throw err;
        } catch (Exception err) {
            throw new ApiException();
        }
    }

    public UserDto create(CreateUserSchema createUserData) throws ApiException {
        try {
            User activeUser = new User();
            activeUser.setEmail(createUserData.getEmail());
            activeUser.setDeletedAt(null);

            Example<User> example = Example.of(activeUser);

            Optional<User> foundUser = this.repository.findOne(example);

            if (foundUser.isPresent()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "User already exists");
            }

            User newUser = new User();
            newUser.setFirstName(createUserData.getFirstName());
            newUser.setLastName(createUserData.getLastName());
            newUser.setPhoneNumber(createUserData.getPhoneNumber());
            newUser.setEmail(createUserData.getEmail());
            newUser.setPassword(this.passwordEncoder.encode(createUserData.getPassword()));

            User createdUser = this.repository.save(newUser);

            return new UserDto("Successfully created user", createdUser);
        } catch (ApiException err) {
            throw err;
        } catch (DataIntegrityViolationException err) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User already exists");
        } catch (Exception err) {
            throw new ApiException();
        }
    }

    public UserDto update(User user, UpdateUserSchema updateUserData) throws ApiException {
        try {
            user.setFirstName(updateUserData.getFirstName());
            user.setLastName(updateUserData.getLastName());

            User updatedUser = this.repository.save(user);

            return new UserDto("Successfully updated user", updatedUser);
        } catch (Exception err) {
            throw new ApiException();
        }
    }

    public UserDto updatePhoneNumber(User user, UpdateUserPhoneNumberSchema updateUserPhoneNumberData) throws ApiException {
        try {
            user.setPhoneNumber(updateUserPhoneNumberData.getPhoneNumber());

            User updatedUser = this.repository.save(user);

            return new UserDto("Successfully updated phone number", updatedUser);
        } catch (Exception err) {
            throw new ApiException();
        }
    }
}
