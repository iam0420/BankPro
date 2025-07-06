package com.bankpro.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a user attempts to register with an email
 * that is already present in the system.
 *
 * Results in a 409 Conflict response.
 */
public class UserAlreadyExistsException extends BaseException {

    private static final String DEFAULT_CODE = "USER_ALREADY_EXISTS";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.CONFLICT;

    /**
     * Constructs a new {@code UserAlreadyExistsException} with the specified email.
     *
     * @param email the email address that already exists
     */
    public UserAlreadyExistsException(String email) {
        super("A user with email '" + email + "' already exists.", DEFAULT_CODE, DEFAULT_STATUS);
    }
}