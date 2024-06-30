package com.gym.tracker.authservice.client;

import com.gym.tracker.authservice.dto.response.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-user-service")
public interface UserClient {
    @GetMapping("/api/users/email")
    UserDTO findByEmail(@RequestParam String email);
}
