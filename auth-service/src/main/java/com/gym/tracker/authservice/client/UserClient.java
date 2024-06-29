package com.gym.tracker.authservice.client;

import com.gym.tracker.authservice.dto.response.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users")
    AppUserDTO findByEmail(@RequestParam String email);
}
