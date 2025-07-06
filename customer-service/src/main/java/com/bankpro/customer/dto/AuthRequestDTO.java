package com.bankpro.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for user login requests.
 * <p>
 * This DTO is used to capture login credentials (email and password)
 * from the client and validate them before authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    /**
     * User's email address used for login.
     * Must be a valid format and not blank.
     */
    @Schema(description = "User's email address", example = "user@example.com", required = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Plain text password used for login.
     * Must not be empty.
     */
    @Schema(description = "User's password", example = "SecurePass123", required = true)
    @NotBlank(message = "Password is required")
    private String password;
}