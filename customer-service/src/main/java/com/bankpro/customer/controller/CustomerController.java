package com.bankpro.customer.controller;

import com.bankpro.customer.dto.*;
import com.bankpro.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling customer-related operations.
 * Implements the {@link CustomerControllerApi} interface.
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController implements CustomerControllerApi {

    private final CustomerService customerService;

    /**
     * Registers a new customer using the provided request payload.
     *
     * @param request the registration request payload
     * @return a {@link ResponseEntity} containing the registered customer's information
     */
    @Override
    public ResponseEntity<RequestResponse<CustomerResponseDTO>> registerCustomer(@Valid CustomerRequestDTO request) {
        CustomerResponseDTO response = customerService.registerCustomer(request);

        RequestResponse<CustomerResponseDTO> apiResponse = RequestResponse.<CustomerResponseDTO>builder()
                .success(true)
                .message("Customer registered successfully")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Authenticates a customer and returns a JWT token upon success.
     *
     * @param request the login credentials
     * @return a {@link ResponseEntity} containing the JWT token
     */
    @Override
    public ResponseEntity<RequestResponse<TokenResponse>> login(@Valid AuthRequestDTO request) {
        String token = customerService.login(request);

        RequestResponse<TokenResponse> apiResponse = RequestResponse.<TokenResponse>builder()
                .success(true)
                .message("Login successful")
                .data(new TokenResponse(token))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Retrieves the profile of the currently authenticated customer.
     *
     * @param authentication the Spring Security authentication object
     * @return a {@link ResponseEntity} with the customer's profile
     */
    @Override
    public ResponseEntity<RequestResponse<CustomerResponseDTO>> getCustomerProfile(Authentication authentication) {
        String email = authentication.getName();
        CustomerResponseDTO response = customerService.getProfile(email);

        RequestResponse<CustomerResponseDTO> apiResponse = RequestResponse.<CustomerResponseDTO>builder()
                .success(true)
                .message("Profile fetched successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Retrieves a list of all registered customers. Admin access required.
     *
     * @return a {@link ResponseEntity} containing the list of customers
     */
    @Override
    public ResponseEntity<RequestResponse<List<CustomerResponseDTO>>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();

        RequestResponse<List<CustomerResponseDTO>> response = RequestResponse.<List<CustomerResponseDTO>>builder()
                .success(true)
                .message("All customers retrieved successfully")
                .data(customers)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Promotes a user to the admin role. Admin access required.
     *
     * @param email the email of the user to promote
     * @return a {@link ResponseEntity} containing the promotion status
     */
    @Override
    public ResponseEntity<RequestResponse<String>> promoteUser(String email) {
        customerService.promoteToAdmin(email);

        RequestResponse<String> response = RequestResponse.<String>builder()
                .success(true)
                .message("User promoted to ROLE_ADMIN")
                .data(email)
                .build();

        return ResponseEntity.ok(response);
    }
}