package com.divakar.SpringSecurityPractice.Service.Impl;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.Enums.Role;
import com.divakar.SpringSecurityPractice.Repository.UserRepository;
import com.divakar.SpringSecurityPractice.Service.AuthenticationService;
import com.divakar.SpringSecurityPractice.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }


}
