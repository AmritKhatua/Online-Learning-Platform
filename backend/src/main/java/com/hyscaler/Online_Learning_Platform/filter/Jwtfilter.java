package com.hyscaler.Online_Learning_Platform.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hyscaler.Online_Learning_Platform.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Jwtfilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get the Authorization header from the request
        String header = request.getHeader("Authorization");

        // Check if the header contains a valid JWT token
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);  // Extract the token from "Bearer <token>"

            // Validate the token
            if (jwtUtil.validateToken(token)) {
                // Extract email from the token
                String email = jwtUtil.getEmailFromToken(token);

                // Create an authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

                // Set the authentication in the security context
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);  // Allow the request to continue to the next filter or endpoint

    }
    
}
