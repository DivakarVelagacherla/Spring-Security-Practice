package com.divakar.SpringSecurityPractice;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.Enums.Role;
import com.divakar.SpringSecurityPractice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityPracticeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User admin = new User();
		User adminInDb = userRepository.findByRole(Role.ADMIN);
		if(adminInDb == null){

			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRole(Role.ADMIN);

			userRepository.save(admin);
		}
	}
}
