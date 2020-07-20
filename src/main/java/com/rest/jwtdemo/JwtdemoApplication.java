package com.rest.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtdemoApplication.class, args);
	}

}
