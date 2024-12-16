package com.hyscaler.Online_Learning_Platform.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    // private final String SECRET_KEY = "yoursecretkey";  // Use a secret key to sign JWTs
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    // Generate JWT token
    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Add role to the token claims

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)  // Set the email (username) as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Set the expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign the token using the secret key
                .compact();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract email from the JWT token
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Extract role from the JWT token
    public String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("role", String.class);
    }
}
