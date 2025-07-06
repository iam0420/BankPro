package com.bankpro.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when authentication fails due to invalid user credentials.
 * This exception is typically thrown during login operations.
 *
 * Consider replacing this with {@link BaseException} based hierarchy
 * if you want standardized error codes and status mapping.
 */
public class InvalidCredentialsException extends BaseException {

    private static final String DEFAULT_CODE = "INVALID_CREDENTIALS";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.UNAUTHORIZED;

    /**
     * Constructs a new {@code InvalidCredentialsException} with the specified detail message.
     *
     * @param message the detail message describing why authentication failed
     */
    public InvalidCredentialsException(String message) {
        super(message, DEFAULT_CODE, DEFAULT_STATUS);
    }
}