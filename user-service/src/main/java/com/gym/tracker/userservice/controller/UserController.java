package com.gym.tracker.userservice.controller;

import com.gym.tracker.userservice.dto.VerifyRequest;
import com.gym.tracker.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyCredentials(@RequestBody VerifyRequest verifyRequest) {
        boolean isValid = userService.verifyCredentials(verifyRequest.getEmail(), verifyRequest.getPassword());
        return ResponseEntity.ok(isValid);
    }
}
