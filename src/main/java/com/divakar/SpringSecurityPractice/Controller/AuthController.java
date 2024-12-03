package com.divakar.SpringSecurityPractice.Controller;


import com.divakar.SpringSecurityPractice.Service.AuthenticationService;
import com.divakar.SpringSecurityPractice.dto.SignInRequest;
import com.divakar.SpringSecurityPractice.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.event.spi.ResolveNaturalIdEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
}
