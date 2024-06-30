package com.gym.tracker.userservice.service;

import com.gym.tracker.userservice.dto.UserDTO;
import com.gym.tracker.userservice.dto.UserRegistrationDTO;
import com.gym.tracker.userservice.entity.AppUser;
import com.gym.tracker.userservice.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final AppUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            ModelMapper modelMapper,
            AppUserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder
            ) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO convertToDto(AppUser appUser) {
        return modelMapper.map(appUser, UserDTO.class);
    }

    public AppUser convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, AppUser.class);
    }

    public UserDTO findUserByEmail(String email) {
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return convertToDto(appUser);
    }

    public void register(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        AppUser newUser = new AppUser();
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        newUser.setRole("USER");

        userRepository.save(newUser);
    }
}
