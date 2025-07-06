package com.bankpro.customer.repository;

import com.bankpro.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Customer} entities.
 * <p>
 * Provides standard CRUD operations, along with custom queries
 * for specific use cases (e.g., finding by email).
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Retrieves a customer by their email address.
     *
     * @param email the unique email address of the customer
     * @return an {@link Optional} containing the {@link Customer} if found, or empty otherwise
     */
    Optional<Customer> findByEmail(String email);
}