package com.serviceuser;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.serviceuser.domain.AppUser;
import com.serviceuser.domain.Role;
import com.serviceuser.service.UserService;

@SpringBootApplication
public class ServiceuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceuserApplication.class, args);
	
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		
		return args -> {
			
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			
			userService.saveUser(new AppUser(null, "John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Will Smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Trent Arnold", "Trent", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_USER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("Trent", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Trent", "ROLE_ADMIN");
			userService.addRoleToUser("Trent", "ROLE_USER");


			

		};
	}
	
}
