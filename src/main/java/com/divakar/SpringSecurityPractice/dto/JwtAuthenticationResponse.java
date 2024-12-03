package com.divakar.SpringSecurityPractice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
