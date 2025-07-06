package com.bankpro.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a user attempts to register with an email
 * address that already exists in the system.
 *
 * <p>This results in an HTTP 409 Conflict response.</p>
 */
public final class EmailAlreadyExistsException extends BaseException {

    /**
     * Constructs a new {@code EmailAlreadyExistsException} with a detailed message.
     *
     * @param email the email address that caused the conflict
     */
    public EmailAlreadyExistsException(String email) {
        super("Email already registered: " + email, "EMAIL_CONFLICT", HttpStatus.CONFLICT);
    }
}