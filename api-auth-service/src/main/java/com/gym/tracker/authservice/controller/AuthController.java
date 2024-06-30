package com.gym.tracker.authservice.controller;

import com.gym.tracker.authservice.dto.request.LoginRequest;
import com.gym.tracker.authservice.dto.response.UserDTO;
import com.gym.tracker.authservice.dto.response.LoginResponse;
import com.gym.tracker.authservice.service.AuthenticationService;
import com.gym.tracker.authservice.utils.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest authRequest) {
        try {
            UserDTO authenticatedUser = authenticationService.login(authRequest);
            LoginResponse loginResponse = LoginResponse.builder()
                    .token(jwtService.generateToken(authenticatedUser))
                    .expiresIn(jwtService.getExpirationTime())
                    .build();

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }
    }
}