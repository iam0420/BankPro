package com.bankpro.customer.enums;

/**
 * Enumeration representing allowed user roles in the system.
 * These roles are used for authorization and access control.
 */
public enum UserRole {

    /**
     * Standard user with limited access.
     */
    ROLE_USER,

    /**
     * Administrative user with elevated privileges.
     */
    ROLE_ADMIN;

    /**
     * Returns true if the given role string matches any defined UserRole.
     *
     * @param role the role string to check
     * @return true if valid role, false otherwise
     */
    public static boolean isValid(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}