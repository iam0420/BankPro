package com.bankpro.customer.service.impl;

import com.bankpro.customer.dto.AuthRequestDTO;
import com.bankpro.customer.dto.CustomerRequestDTO;
import com.bankpro.customer.dto.CustomerResponseDTO;
import com.bankpro.customer.entity.Customer;
import com.bankpro.customer.enums.UserRole;
import com.bankpro.customer.exception.InvalidCredentialsException;
import com.bankpro.customer.exception.ResourceNotFoundException;
import com.bankpro.customer.exception.UserAlreadyExistsException;
import com.bankpro.customer.repository.CustomerRepository;
import com.bankpro.customer.service.CustomerService;
import com.bankpro.customer.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link CustomerService} interface.
 * Handles business logic related to customer operations like registration,
 * authentication, profile retrieval, admin promotion, etc.
 * <p>
 * Note: Token generation and password encoding are commented for future integration.
 *
 * @author Aniket Kamlesh
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepo;
     private final PasswordEncoder passwordEncoder;
     private final JwtUtil jwtUtil;

    private final Environment env;

    @Value("${app.security.default-role:ROLE_USER}")
    private String defaultRole;

    /**
     * Registers a new customer with optional admin role in dev profile.
     */
    @Override
    public CustomerResponseDTO registerCustomer(CustomerRequestDTO request) {
        log.info("Registering new customer with email: {}", request.getEmail());

        if (customerRepo.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Customer already exists with email: {}", request.getEmail());
            throw new UserAlreadyExistsException("Customer with this email already exists.");
        }

        String roleToAssign = defaultRole;

        // Allow ROLE_ADMIN assignment only in dev profile
        if (isDevProfile() && request.getRole() != null) {
            roleToAssign = request.getRole().name();
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                // .password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword()) // Replace after enabling encoder
                .phone(request.getPhone())
                .role(roleToAssign)
                .build();

        Customer saved = customerRepo.save(customer);
        log.info("Customer registered successfully: ID = {}", saved.getId());

        return mapToResponseDTO(saved);
    }

    /**
     * Authenticates the user and returns a token.
     */
    @Override
    public String login(AuthRequestDTO request) {
        log.info("Login attempt for email: {}", request.getEmail());

        Customer customer = customerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("Login failed: Email not found");
                    return new ResourceNotFoundException("Customer not found with email: " + request.getEmail());
                });


        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            log.warn("Invalid credentials for email: {}", request.getEmail());
            throw new InvalidCredentialsException("Incorrect email or password.");
        }

        String token = jwtUtil.generateToken(customer.getEmail(), customer.getRole());
        log.info("Token generated for customer: {}", customer.getEmail());
        return token;

    }

    /**
     * Retrieves customer profile by email.
     */
    @Override
    public CustomerResponseDTO getProfile(String email) {
        log.info("Fetching profile for email: {}", email);

        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Profile fetch failed: Customer not found for email {}", email);
                    return new ResourceNotFoundException("Customer not found");
                });

        return mapToResponseDTO(customer);
    }

    /**
     * Returns a list of all customers.
     */
    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Promotes a user to admin role based on email.
     */
    @Override
    public void promoteToAdmin(String email) {
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setRole(UserRole.ROLE_ADMIN.name());
        customerRepo.save(customer);
        log.info("Customer promoted to ROLE_ADMIN: {}", email);
    }

    /**
     * Converts a {@link Customer} entity to {@link CustomerResponseDTO}.
     */
    private CustomerResponseDTO mapToResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    /**
     * Utility method to check if the active Spring profile is "dev".
     */
    private boolean isDevProfile() {
        return Arrays.asList(env.getActiveProfiles()).contains("dev");
    }
}