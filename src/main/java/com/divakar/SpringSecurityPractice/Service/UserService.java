package com.divakar.SpringSecurityPractice.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
}
