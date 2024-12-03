package com.divakar.SpringSecurityPractice.Service.Impl;

import com.divakar.SpringSecurityPractice.Repository.UserRepository;
import com.divakar.SpringSecurityPractice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private Optional<UserDetails> userDetails;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                userDetails = userRepository.findByEmail(userName);
                return userDetails.orElse(null);
            }
        };
    }
}
