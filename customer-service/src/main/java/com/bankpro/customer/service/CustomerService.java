package com.bankpro.customer.service;

import com.bankpro.customer.dto.AuthRequestDTO;
import com.bankpro.customer.dto.CustomerRequestDTO;
import com.bankpro.customer.dto.CustomerResponseDTO;

import java.util.List;

/**
 * Service interface for handling customer-related business logic.
 * Provides operations for customer registration, authentication,
 * profile retrieval, listing all customers, and role promotion.
 *
 * @author Aniket Kamlesh
 */
public interface CustomerService {

    /**
     * Registers a new customer in the system.
     *
     * @param request the registration request containing customer details
     * @return the newly created customer response DTO
     */
    CustomerResponseDTO registerCustomer(CustomerRequestDTO request);

    /**
     * Authenticates the customer and returns a valid JWT token.
     *
     * @param request login credentials (email and password)
     * @return a signed JWT token if credentials are valid
     */
    String login(AuthRequestDTO request);

    /**
     * Fetches the customer profile based on the email.
     *
     * @param email the email of the authenticated customer
     * @return the profile details of the customer
     */
    CustomerResponseDTO getProfile(String email);

    /**
     * Retrieves a list of all registered customers.
     * This operation is typically restricted to admin users.
     *
     * @return list of all customer response DTOs
     */
    List<CustomerResponseDTO> getAllCustomers();

    /**
     * Promotes a customer to an administrator role.
     * Only users with admin access can invoke this.
     *
     * @param email the email of the customer to be promoted
     */
    void promoteToAdmin(String email);
}