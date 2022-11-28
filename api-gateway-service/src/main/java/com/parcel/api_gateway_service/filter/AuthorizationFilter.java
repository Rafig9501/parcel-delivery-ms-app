package com.parcel.api_gateway_service.filter;

import com.google.common.net.HttpHeaders;
import com.parcel.api_gateway_service.exception.AuthenticationException;
import com.parcel.api_gateway_service.exception.TokenExpiredException;
import com.parcel.api_gateway_service.exception.TokenNotFoundException;
import com.parcel.api_gateway_service.gateway.RouterValidator;
import com.parcel.api_gateway_service.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class AuthorizationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            log.info("validating if header has JWT token");
            if (!routerValidator.isSecured.test(exchange.getRequest())) {
                throw new TokenNotFoundException("JWT Token not provided in Header");
            }
            log.info("validating expiration of JWT token");
            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            if (!jwtUtil.isInvalid(authHeader)) {
                throw new TokenExpiredException("token has been expired");
            }
            log.info("adding headers to request");
            Claims claims = jwtUtil.getAllClaimsFromToken(authHeader);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id")));
            exchange.getRequest().mutate().header("role", String.valueOf(claims.get("role")));
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("Error Validating Authentication Header", e);
            throw new AuthenticationException("Error Validating Authentication Header " + e.getMessage());
        }
    }
}
//    ErrorResponseDto error = ErrorResponseDto.builder().timestamp(LocalDateTime.now()).status(HttpStatus.UNAUTHORIZED.value())
//            .message(e.getMessage()).error(e.getCause().toString()).path(exchange.getRequest().getURI().toString()).build();
//    byte[] bytes = SerializationUtils.serialize(error);
//    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().writeWith(Flux.just(buffer));
