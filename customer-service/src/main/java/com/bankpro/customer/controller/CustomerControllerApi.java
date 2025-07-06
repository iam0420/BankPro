package com.bankpro.customer.controller;

import com.bankpro.customer.constant.ApiPaths;
import com.bankpro.customer.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Public API interface for handling customer account operations.
 * <p>
 * Includes registration, login, profile retrieval, admin listing, and promotion actions.
 * </p>
 *
 * @author Aniket Kamlesh
 */
@Tag(name = "Customer Controller", description = "Endpoints for customer registration, login, and profile management")
@RequestMapping(ApiPaths.BASE_CUSTOMER)
public interface CustomerControllerApi {

    /**
     * Register a new customer account.
     *
     * @param request request payload with user information
     * @return success response containing registered customer details
     */
    @Operation(
            summary = "Register a new customer",
            description = "Creates a customer account using name, email, password, and phone number.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CustomerRequestDTO.class),
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "New Customer",
                                    value = """
                                            {
                                              "name": "Ankit Kumar",
                                              "email": "ankit@example.com",
                                              "password": "StrongPass123",
                                              "phone": "9876543210"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer registered successfully"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    @PostMapping("/register")
    ResponseEntity<RequestResponse<CustomerResponseDTO>> registerCustomer(
            @Valid @org.springframework.web.bind.annotation.RequestBody CustomerRequestDTO request
    );

    /**
     * Authenticate a customer and return a JWT token.
     *
     * @param request login credentials
     * @return token response if authentication is successful
     */
    @Operation(
            summary = "Login as customer",
            description = "Authenticates user credentials and returns a JWT token.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AuthRequestDTO.class),
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Login Example",
                                    value = """
                                            {
                                              "email": "ankit@example.com",
                                              "password": "StrongPass123"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    ResponseEntity<RequestResponse<TokenResponse>> login(
            @Valid @org.springframework.web.bind.annotation.RequestBody AuthRequestDTO request
    );

    /**
     * Get the profile details of the currently authenticated user.
     *
     * @param authentication Spring Security context
     * @return authenticated user's profile data
     */
    @Operation(
            summary = "Fetch current user profile",
            description = "Returns details of the authenticated user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile retrieved"),
            @ApiResponse(responseCode = "403", description = "Access denied or token missing")
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/profile")
    ResponseEntity<RequestResponse<CustomerResponseDTO>> getCustomerProfile(Authentication authentication);

    /**
     * List all registered customers. Admin only.
     *
     * @return list of customers
     */
    @Operation(
            summary = "List all customers (Admin only)",
            description = "Retrieves a list of all registered users. Accessible only to admins."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List returned"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/customers")
    ResponseEntity<RequestResponse<List<CustomerResponseDTO>>> getAllCustomers();

    /**
     * Promote an existing customer to admin.
     *
     * @param email email of the customer to promote
     * @return success message
     */
    @Operation(
            summary = "Promote user to admin role",
            description = "Allows admin to promote another user to ROLE_ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User promoted"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/promote/{email}")
    ResponseEntity<RequestResponse<String>> promoteUser(@PathVariable String email);
}