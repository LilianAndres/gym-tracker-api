package com.gym.tracker.userservice.configuration;

import com.gym.tracker.userservice.entity.User;
import com.gym.tracker.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitializer {
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User(null, "user1@example.com", passwordEncoder.encode("password1"), "ROLE_USER"));
            userRepository.save(new User(null, "user2@example.com", passwordEncoder.encode("password2"), "ROLE_USER"));
            userRepository.save(new User(null, "admin@example.com", passwordEncoder.encode("adminpassword"), "ROLE_ADMIN"));
        };
    }
}
