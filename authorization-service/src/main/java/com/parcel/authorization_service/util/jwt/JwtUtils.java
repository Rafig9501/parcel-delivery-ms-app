package com.parcel.authorization_service.util.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Log4j2
@Component
public class JwtUtils {

    @Value("${security.jwt_secret}")
    private String jwtSecret;

    @Value("${security.jwt_access_token_expiration}")
    private int jwtExpirationMs;

    public String getJwtFromRequest(HttpServletRequest request) {
        return request.getHeader("JWT");
    }

    public String getUserIdFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return "Validated";
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            return "Invalid JWT signature";
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return "Invalid JWT token";
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            return "JWT token is expired";
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return "JWT token is unsupported";
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return "JWT claims string is empty";
        } catch (Exception e) {
            log.error("Error occurred in JWT validation {} ", e.getMessage());
            return "Error occurred in JWT validation";
        }
    }

    public String generateTokenFromUserId(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
