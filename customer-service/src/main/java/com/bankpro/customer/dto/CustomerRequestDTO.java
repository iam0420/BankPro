package com.bankpro.customer.dto;

import com.bankpro.customer.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO for capturing customer registration data.
 * Used when a new user registers into the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Payload for creating a new customer account")
public class CustomerRequestDTO {

    /**
     * Full name of the customer.
     */
    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the customer", example = "Amit Kumar", required = true)
    private String name;

    /**
     * Email address of the customer.
     * Must be in a valid email format.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Email address of the customer", example = "amit.kumar@example.com", required = true)
    private String email;

    /**
     * Password for account authentication.
     * Must be at least 6 characters long.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "User's password", example = "SecurePass123", required = true)
    private String password;

    /**
     * 10-digit phone number of the customer.
     */
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Schema(description = "10-digit phone number", example = "9876543210", required = true)
    private String phone;

    /**
     * Optional role assignment (e.g., ROLE_USER or ROLE_ADMIN).
     * Used internally or for development purposes only.
     */
    @Schema(
            description = "Optional user role (default is ROLE_USER). Allowed only in dev/test environments.",
            allowableValues = {"ROLE_USER", "ROLE_ADMIN"},
            example = "ROLE_USER",
            required = false
    )
    private UserRole role;
}