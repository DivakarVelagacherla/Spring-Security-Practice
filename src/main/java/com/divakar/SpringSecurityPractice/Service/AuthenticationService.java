package com.divakar.SpringSecurityPractice.Service;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.dto.JwtAuthenticationResponse;
import com.divakar.SpringSecurityPractice.dto.RefreshTokenRequest;
import com.divakar.SpringSecurityPractice.dto.SignInRequest;
import com.divakar.SpringSecurityPractice.dto.SignUpRequest;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
