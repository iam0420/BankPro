package com.bankpro.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Generic response wrapper for all API endpoints.
 * Encapsulates success status, user-facing message, and response data payload.
 *
 * @param <T> the type of the response body
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Standard API response wrapper")
public class RequestResponse<T> {

    /**
     * Indicates whether the request was successful.
     */
    @Schema(description = "Success status of the response", example = "true")
    private boolean success;

    /**
     * Descriptive message related to the operation.
     */
    @Schema(description = "Human-readable message about the result", example = "Customer registered successfully")
    private String message;

    /**
     * The actual payload/data returned from the API.
     */
    @Schema(description = "Payload returned from the API")
    private T data;
}