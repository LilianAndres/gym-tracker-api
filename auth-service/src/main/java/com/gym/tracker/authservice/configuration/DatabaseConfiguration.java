package com.gym.tracker.authservice.configuration;

import com.gym.tracker.authservice.entity.AppUser;
import com.gym.tracker.authservice.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseConfiguration {
    private final PasswordEncoder passwordEncoder;

    public DatabaseConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(AppUserRepository appUserRepository) {
        return args -> {
            appUserRepository.save(new AppUser(null, "user1@example.com", passwordEncoder.encode("password1"), "ROLE_USER"));
            appUserRepository.save(new AppUser(null, "user2@example.com", passwordEncoder.encode("password2"), "ROLE_USER"));
            appUserRepository.save(new AppUser(null, "admin@example.com", passwordEncoder.encode("adminpassword"), "ROLE_ADMIN"));
        };
    }
}
