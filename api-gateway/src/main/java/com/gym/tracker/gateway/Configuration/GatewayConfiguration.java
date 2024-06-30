package com.gym.tracker.gateway.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("api-auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://api-auth-service"))
                .route("api-user-service", r -> r.path("/api/users/**")
                        .uri("lb://api-user-service"))
                .build();
    }
}
