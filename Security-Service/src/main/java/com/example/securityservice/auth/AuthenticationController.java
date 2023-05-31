package com.example.securityservice.auth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9090")
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    public final UserAuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(
            @RequestBody AuthenticationRequest request
    ){
        log.info("hello");
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/validate")
    public ResponseEntity<AuthenticationResponse>validate(
            @RequestBody ValidationRequest request
    ){
        return ResponseEntity.ok(service.validateToken(request));
    }
}