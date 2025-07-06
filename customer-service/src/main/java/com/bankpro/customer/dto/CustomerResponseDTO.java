package com.bankpro.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO used to return customer details in API responses.
 * Typically returned after registration, login, or profile fetch operations.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "CustomerResponseDTO", description = "Response model representing customer details")
public class CustomerResponseDTO {

    /**
     * Unique identifier for the customer.
     */
    @Schema(description = "Unique ID assigned to the customer", example = "101")
    private Long id;

    /**
     * Full name of the customer.
     */
    @Schema(description = "Full name of the customer", example = "Ravi Sharma")
    private String name;

    /**
     * Registered email address of the customer.
     */
    @Schema(description = "Customer's email address", example = "ravi.sharma@example.com")
    private String email;

    /**
     * Phone number associated with the customer account.
     */
    @Schema(description = "10-digit phone number", example = "9876543210")
    private String phone;
}