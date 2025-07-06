package com.bankpro.customer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Abstract base class for all custom application exceptions.
 * <p>
 * It encapsulates an HTTP status code and a machine-readable error code,
 * allowing consistent error handling and API responses.
 * </p>
 *
 * <p>
 * Extend this class for domain-specific exceptions like:
 * {@code UserAlreadyExistsException}, {@code InvalidCredentialsException}, etc.
 * </p>
 */
@Getter
public abstract class BaseException extends RuntimeException {

    /**
     * The HTTP status code to be returned with the response.
     */
    private final HttpStatus status;

    /**
     * A machine-readable error code used for API clients and debugging.
     */
    private final String code;

    /**
     * Constructs a new BaseException with the given message, code, and status.
     *
     * @param message a human-readable error message
     * @param code    a machine-readable error code (e.g., "USER_EXISTS")
     * @param status  the corresponding HTTP status
     */
    protected BaseException(String message, String code, HttpStatus status) {
        super(message);
        this.status = status;
        this.code = code;
    }
}