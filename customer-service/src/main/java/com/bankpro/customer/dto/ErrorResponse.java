package com.bankpro.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard structure for error responses returned by the API.
 * This DTO is used to encapsulate all relevant error metadata
 * in a consistent format across endpoints.
 *
 * <p>Example usage:</p>
 * <pre>
 * {
 *   "timestamp": "2025-07-06T21:12:34.123",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "The email address is already in use",
 *   "path": "/api/v1/customers/register"
 * }
 * </pre>
 *
 * @author aniket kamlesh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Represents a structured error returned by the API")
public class ErrorResponse {

    /**
     * The exact timestamp when the error occurred.
     */
    @Schema(
            description = "Timestamp when the error was generated",
            example = "2025-07-06T21:12:34.123"
    )
    private LocalDateTime timestamp;

    /**
     * HTTP status code corresponding to the error (e.g., 400, 404, 500).
     */
    @Schema(
            description = "HTTP status code representing the error",
            example = "400"
    )
    private int status;

    /**
     * Human-readable HTTP status reason phrase (e.g., "Bad Request").
     */
    @Schema(
            description = "HTTP status description (e.g., 'Bad Request')",
            example = "Bad Request"
    )
    private String error;

    /**
     * Descriptive message explaining the root cause of the error.
     */
    @Schema(
            description = "Detailed message explaining the error",
            example = "Email address already exists"
    )
    private String message;

    /**
     * The URI path of the HTTP request that triggered the error.
     */
    @Schema(
            description = "API endpoint where the error occurred",
            example = "/api/v1/customers/register"
    )
    private String path;
}