package com.bankpro.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a standard error response returned by the API
 * for failed HTTP operations.
 * <p>
 * This structure ensures consistency in all error payloads
 * across the service.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    /**
     * The exact timestamp when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status code (e.g. 400, 404, 500).
     */
    private int status;

    /**
     * The HTTP status description (e.g. "Bad Request", "Internal Server Error").
     */
    private String error;

    /**
     * The human-readable message describing the cause of the error.
     */
    private String message;

    /**
     * The URI path of the request that caused the error.
     */
    private String path;
}