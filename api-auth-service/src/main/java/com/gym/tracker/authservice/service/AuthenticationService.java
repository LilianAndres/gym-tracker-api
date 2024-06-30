package com.gym.tracker.authservice.service;

import com.gym.tracker.authservice.client.UserClient;
import com.gym.tracker.authservice.dto.request.LoginRequest;
import com.gym.tracker.authservice.dto.response.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserClient userClient;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserClient userClient
    ) {
        this.authenticationManager = authenticationManager;
        this.userClient = userClient;
    }

    public UserDTO login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDTO userDTO = userClient.findByEmail(loginRequest.getEmail());
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDTO;
    }
}
