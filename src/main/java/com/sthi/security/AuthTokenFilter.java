package com.sthi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthTokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    
    // List of public endpoints that don't require authentication
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
        "/api/auth/signin",
        "/api/auth/signup",
        "/api/test/all",
        "/h2-console/",
        "/swagger-ui/",
        "/v3/api-docs",
        "/favicon.ico"
    );
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String requestPath = request.getServletPath();
        String jwt = parseJwt(request);
        
        logger.debug("Processing request to: {} with JWT present: {}", requestPath, jwt != null);
        
        try {
            if (jwt != null) {
                if (jwtUtils.validateJwtToken(jwt)) {
                    String email = jwtUtils.getEmailFromJwtToken(jwt);
                    
                    logger.debug("JWT validated for user: {}", email);
                    
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                    logger.info("Authenticated user: {} for path: {}", email, requestPath);
                    
                } else {
                    logger.warn("Invalid JWT token for request to: {}", requestPath);
                }
            } else {
                logger.debug("No JWT token found for request to: {}", requestPath);
            }
        } catch (UsernameNotFoundException e) {
            logger.error("User not found for JWT token: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
            // Don't throw exception to allow the request to continue (might be a public endpoint)
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String parseJwt(HttpServletRequest request) {
        // Check Authorization header first
        String headerAuth = request.getHeader("Authorization");
        
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.substring(7);
            logger.debug("Found JWT in Authorization header");
            return token;
        }
        
        // Check for token in query parameter (optional)
        // ONLY if not a multipart request to avoid premature parsing issues or large body consumption
        String contentType = request.getContentType();
        if (contentType == null || !contentType.toLowerCase().startsWith("multipart/")) {
            String tokenParam = request.getParameter("token");
            if (StringUtils.hasText(tokenParam)) {
                logger.debug("Found JWT in query parameter");
                return tokenParam;
            }
        }
        
        // Check for token in cookie (optional)
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if ("token".equals(cookie.getName()) && StringUtils.hasText(cookie.getValue())) {
                    logger.debug("Found JWT in cookie");
                    return cookie.getValue();
                }
            }
        }
        
        return null;
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        
        // Skip JWT filter for public endpoints
        boolean shouldNotFilter = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        
        if (shouldNotFilter) {
            logger.debug("Skipping JWT filter for public path: {}", path);
        }
        
        return shouldNotFilter;
    }
}