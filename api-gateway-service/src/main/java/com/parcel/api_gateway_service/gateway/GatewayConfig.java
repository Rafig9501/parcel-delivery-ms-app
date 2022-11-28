package com.parcel.api_gateway_service.gateway;

import com.parcel.api_gateway_service.filter.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@RefreshScope
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthorizationFilter authorizationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/user-service/**")
                        .filters(f -> f.filter(authorizationFilter))
                        .uri("http://localhost:8080/user-service"))
                .route("auth", r -> r.path("/auth-service/**")
                        .filters(f -> f.filter(authorizationFilter))
                        .uri("http://localhost:8080/auth-service"))
                .route("courier", r -> r.path("/courier-service/**")
                        .filters(f -> f.filter(authorizationFilter))
                        .uri("http://localhost:8080/courier-service"))
                .build();
    }
}
