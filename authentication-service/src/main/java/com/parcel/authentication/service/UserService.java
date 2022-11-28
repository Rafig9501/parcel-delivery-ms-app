package com.parcel.authentication.service;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.dto.request.UserAuthenticationRequestDto;
import com.parcel.authentication.dto.request.UserSignUpRequestDto;
import com.parcel.authentication.dto.response.UserAuthenticationResponseDto;
import com.parcel.authentication.dto.response.UserSignUpResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserSignUpResponseDto createUser(UserSignUpRequestDto dto);

    UserAuthenticationResponseDto signIn(UserAuthenticationRequestDto dto);

    UserAuthenticationResponseDto refreshToken(TokenRequestDto dto);
}
