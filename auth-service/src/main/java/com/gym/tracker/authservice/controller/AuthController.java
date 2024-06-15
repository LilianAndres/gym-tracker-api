package com.gym.tracker.authservice.controller;

import com.gym.tracker.authservice.dto.request.LoginRequest;
import com.gym.tracker.authservice.dto.response.LoginResponse;
import com.gym.tracker.authservice.entity.AppUser;
import com.gym.tracker.authservice.service.AuthenticationService;
import com.gym.tracker.common.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest authRequest) {
        AppUser authenticatedUser = authenticationService.login(authRequest);
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtService.generateToken(authenticatedUser))
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}