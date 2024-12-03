package com.divakar.SpringSecurityPractice.Service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtUtil {

    String generateToken(UserDetails userDetails);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails UserDetails);
}

