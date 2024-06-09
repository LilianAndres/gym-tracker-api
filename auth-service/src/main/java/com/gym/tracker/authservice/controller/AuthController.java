package com.gym.tracker.authservice.controller;

import com.gym.tracker.authservice.dto.LoginRequest;
import com.gym.tracker.authservice.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final RestTemplate restTemplate;

    private final JwtService jwtService;

    public AuthController(RestTemplate restTemplate, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest authRequest) {
        String url = "http://localhost:8083/api/users/verify";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, authRequest, Boolean.class);
        boolean isAuthenticated = Boolean.TRUE.equals(response.getBody());
        if (isAuthenticated) {
            String token = jwtService.createToken(authRequest.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return ResponseEntity.ok("Token is valid");
    }
}