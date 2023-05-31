package com.example.securityservice.auth;

import com.example.securityservice.config.user.JwtService;
import com.example.securityservice.user.User;
import com.example.securityservice.user.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AdminAuthenticationService {

    public UserRepo repository;
    public PasswordEncoder passwordEncoder;
    public JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse registerAdmin(User user, UserRepo userRepository) {

        passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jwtService = new JwtService();
        String token = jwtService.generateToken(user);

        userRepository.save(user);
        return AuthenticationResponse.builder().token(token).build();

    }
}
