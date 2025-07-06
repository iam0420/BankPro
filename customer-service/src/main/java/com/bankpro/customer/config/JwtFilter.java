package com.bankpro.customer.config;

import com.bankpro.customer.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT authentication filter responsible for validating the token in each HTTP request.
 * Extracts user identity and role from token and sets authentication context accordingly.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    /**
     * Intercepts requests and applies JWT validation.
     *
     * @param request     HTTP request
     * @param response    HTTP response
     * @param filterChain filter chain
     * @throws ServletException when servlet fails
     * @throws IOException      when I/O fails
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String requestURI = request.getRequestURI();
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Process JWT only if Bearer token is present
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7); // Strip "Bearer "

                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.extractUsername(token);
                    String role = jwtUtil.extractRole(token);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    List.of(new SimpleGrantedAuthority(role))
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.debug("JWT validated for user [{}] with role [{}] on URI [{}]", username, role, requestURI);
                } else {
                    log.warn("Invalid JWT token for URI: {}", requestURI);
                }

            } catch (ExpiredJwtException ex) {
                log.warn("Expired JWT token encountered on URI [{}]: {}", requestURI, ex.getMessage());
            } catch (Exception ex) {
                log.error("Error occurred while parsing JWT on URI [{}]: {}", requestURI, ex.getMessage(), ex);
            }
        }

        filterChain.doFilter(request, response);
    }
}