package com.divakar.SpringSecurityPractice.Service.Impl;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.Enums.Role;
import com.divakar.SpringSecurityPractice.Repository.UserRepository;
import com.divakar.SpringSecurityPractice.Service.AuthenticationService;
import com.divakar.SpringSecurityPractice.Service.JwtUtil;
import com.divakar.SpringSecurityPractice.dto.JwtAuthenticationResponse;
import com.divakar.SpringSecurityPractice.dto.RefreshTokenRequest;
import com.divakar.SpringSecurityPractice.dto.SignInRequest;
import com.divakar.SpringSecurityPractice.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword()
        ));

        UserDetails userInDb = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password!"));

        String token = jwtUtil.generateToken(userInDb);
        String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(),userInDb);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userName = jwtUtil.extractUserName(refreshTokenRequest.getToken());
        UserDetails user = userRepository.findByEmail(userName).orElseThrow();
        if (jwtUtil.validateToken(refreshTokenRequest.getToken(),user)){
            String token = jwtUtil.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(token);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }

        return null;
    }
}
