package com.gym.tracker.authservice.controller;

import com.gym.tracker.authservice.dto.request.LoginRequest;
import com.gym.tracker.authservice.dto.response.UserDTO;
import com.gym.tracker.authservice.dto.response.LoginResponse;
import com.gym.tracker.authservice.service.AuthenticationService;
import com.gym.tracker.authservice.utils.JwtService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(
            JwtService jwtService,
            AuthenticationService authenticationService
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest authRequest) {
        UserDTO authenticatedUser = authenticationService.login(authRequest);
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtService.generateToken(authenticatedUser))
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}