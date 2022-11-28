package com.parcel.authentication.service.impl;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.entity.TokenEntity;
import com.parcel.authentication.exception.TokenNotFoundException;
import com.parcel.authentication.exception.TokenRefreshException;
import com.parcel.authentication.exception.UserNotFoundException;
import com.parcel.authentication.repository.TokenEntityRepository;
import com.parcel.authentication.repository.UserRepository;
import com.parcel.authentication.service.TokenEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenEntityServiceImpl implements TokenEntityService {

    @Value("${security.jwt_refresh_token_expiration}")
    private Long tokenEntityDurationMs;
    private final TokenEntityRepository tokenEntityRepository;
    private final UserRepository userRepository;

    @Override
    public TokenEntity createTokenEntity(UUID userId) {
        log.info("creating token for user id = {}", userId);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("no user found")).getId());
        tokenEntity.setExpiryDate(Instant.now().plusMillis(tokenEntityDurationMs));
        tokenEntity.setToken(UUID.randomUUID().toString());
        tokenEntity = tokenEntityRepository.save(tokenEntity);
        return tokenEntity;
    }

    @Override
    public TokenEntity verifyExpiration(TokenRequestDto dto) {
        TokenEntity token = tokenEntityRepository.findByToken(dto.getRefreshToken())
                .orElseThrow(() -> new TokenNotFoundException("token not found"));
        log.info("verifying token {}", token.getToken());
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            tokenEntityRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh token was expired. Please make a new sign in request");
        }
        return token;
    }
}
