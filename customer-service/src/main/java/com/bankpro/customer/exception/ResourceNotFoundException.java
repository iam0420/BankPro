package com.bankpro.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found in the system.
 * Typically used for 404 Not Found responses.
 *
 * For example: User not found, Order not found, etc.
 */
public class ResourceNotFoundException extends BaseException {

    private static final String DEFAULT_CODE = "RESOURCE_NOT_FOUND";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    /**
     * Constructs a new {@code ResourceNotFoundException} with a custom message.
     *
     * @param message detailed explanation of which resource was not found
     */
    public ResourceNotFoundException(String message) {
        super(message, DEFAULT_CODE, DEFAULT_STATUS);
    }
}