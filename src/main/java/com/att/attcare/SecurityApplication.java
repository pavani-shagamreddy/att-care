package com.att.attcare;

import com.att.attcare.auth.AuthenticationService;
import com.att.attcare.auth.RegisterRequest;
import com.att.attcare.model.Role;

import static com.att.attcare.model.Role.ADMIN;
import static com.att.attcare.model.Role.DOCTOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	
	/*
	 * @Bean public CommandLineRunner commandLineRunner( AuthenticationService
	 * service ) { return args -> { var admin = RegisterRequest.builder()
	 * .name("Admin") .email("admin@gmail.com") .password("admin") .role(ADMIN)
	 * .build(); service.register(admin);
	 * 
	 * }; }
	 */
	 
	 
	 

}
