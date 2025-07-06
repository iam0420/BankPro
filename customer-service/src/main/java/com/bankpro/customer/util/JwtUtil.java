package com.bankpro.customer.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for generating and validating JSON Web Tokens (JWTs).
 * Encapsulates logic for signing, parsing, and extracting claims from JWTs.
 *
 * @author Aniket Kamlesh
 */
@Component
public class JwtUtil {

    /**
     * Secret key used to sign JWTs using HMAC-SHA-256 algorithm.
     * This key is generated once per application instance.
     * In production, store and retrieve this from a secure vault or environment variable.
     */
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Token expiration time in milliseconds (10 hours).
     */
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Generates a JWT token with subject and custom "role" claim.
     *
     * @param email the user's email (used as subject)
     * @param role  the user's role
     * @return signed JWT as a String
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extracts the username (subject) from the token.
     *
     * @param token JWT string
     * @return username/email from token subject
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Extracts the role from the token's claims.
     *
     * @param token JWT string
     * @return role from the "role" claim
     */
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    /**
     * Validates the given JWT token for signature and expiration.
     *
     * @param token JWT string
     * @return true if valid, false if expired or tampered
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parses the token and extracts all claims.
     *
     * @param token JWT string
     * @return Claims object containing payload
     */
    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}