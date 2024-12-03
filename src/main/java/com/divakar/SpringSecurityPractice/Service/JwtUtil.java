package com.divakar.SpringSecurityPractice.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {

    String generateToken(UserDetails userDetails);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);


}

