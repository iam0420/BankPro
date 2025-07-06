package com.bankpro.customer.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a registered customer in the system.
 */
@Entity
@Table(name = "customers", indexes = {
        @Index(name = "idx_customer_email", columnList = "email", unique = true),
        @Index(name = "idx_customer_phone", columnList = "phone", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the customer.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email address (must be unique).
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Hashed password (stored securely).
     */
    @Column(nullable = false)
    private String password;

    /**
     * Phone number (must be unique).
     */
    @Column(unique = true)
    private String phone;

    /**
     * Role of the user (e.g., ROLE_USER, ROLE_ADMIN).
     */
    @Column(nullable = false)
    private String role;
}