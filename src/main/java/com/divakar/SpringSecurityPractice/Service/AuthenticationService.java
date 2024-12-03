package com.divakar.SpringSecurityPractice.Service;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.dto.SignUpRequest;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
}
