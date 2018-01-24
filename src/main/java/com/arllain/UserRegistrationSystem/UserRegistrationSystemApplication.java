package com.arllain.UserRegistrationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.arllain.UserRegistrationSystem.service"})
@EntityScan("com.arllain.UserRegistrationSystem.dto")
@EnableJpaRepositories("com.arllain.UserRegistrationSystem.repository")
public class UserRegistrationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationSystemApplication.class, args);
	}
	
}
