package com.divakar.SpringSecurityPractice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
