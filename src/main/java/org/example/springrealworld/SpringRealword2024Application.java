package org.example.springrealworld;

import org.example.springrealworld.model.User;
import org.example.springrealworld.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringRealword2024Application {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfNotExist(userRepository, passwordEncoder);
        };
    }
    private void createUserIfNotExist(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        String email = "test@example.com"; // Define the email of the test user
        userRepository.findByEmail(email).orElseGet(() -> {
            User testUser = new User();
            testUser.setEmail(email);
            testUser.setUsername("testUser");
            testUser.setPassword(passwordEncoder.encode("password")); // Use a password encoder
            testUser.setBio("This is a test user.");
            testUser.setImage("http://example.com/testuser.png");
            return userRepository.save(testUser);
        });
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringRealword2024Application.class, args);
    }


}
