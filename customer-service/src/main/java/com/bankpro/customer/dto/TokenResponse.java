package com.bankpro.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Response DTO that holds the JWT token issued after successful authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "JWT token response after successful login")
public class TokenResponse {

    /**
     * JWT token string used for authenticating subsequent requests.
     */
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}