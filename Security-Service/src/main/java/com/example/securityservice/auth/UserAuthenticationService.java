package com.example.securityservice.auth;

import com.example.securityservice.config.user.JwtService;
import com.example.securityservice.user.Role;
import com.example.securityservice.user.User;
import com.example.securityservice.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationService.class);
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("authenticationResponse");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse validateToken(ValidationRequest validationRequest){

        if(jwtService.isTokenValidToken(validationRequest.getToken())){
            return AuthenticationResponse.builder()
                    .token(validationRequest.getToken())
                    .build();
        }
        return null;

    }
}
