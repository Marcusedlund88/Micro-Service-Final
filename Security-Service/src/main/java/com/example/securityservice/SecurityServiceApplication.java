package com.example.securityservice;

import com.example.securityservice.auth.AdminAuthenticationService;
import com.example.securityservice.user.Role;
import com.example.securityservice.user.User;
import com.example.securityservice.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner customers(UserRepo userRepository) {
		AdminAuthenticationService authenticationService = new AdminAuthenticationService();
		return (args) -> {
			User user1 = new User();
			user1.setFirstname("MarcusH");
			user1.setUsername("Admin");
			user1.setPassword("admin");
			user1.setRole(Role.ADMIN);
			authenticationService.registerAdmin(user1, userRepository);
		};
	}
}
