package com.parcel.authentication.service;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.entity.TokenEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TokenEntityService {

    TokenEntity createTokenEntity(UUID userId);

    TokenEntity verifyExpiration(TokenRequestDto dto);
}
