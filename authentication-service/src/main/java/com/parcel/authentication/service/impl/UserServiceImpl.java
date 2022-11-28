package com.parcel.authentication.service.impl;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.dto.request.UserAuthenticationRequestDto;
import com.parcel.authentication.dto.request.UserSignUpRequestDto;
import com.parcel.authentication.dto.response.UserAuthenticationResponseDto;
import com.parcel.authentication.dto.response.UserSignUpResponseDto;
import com.parcel.authentication.dto.security.UserSecurityDetailsDto;
import com.parcel.authentication.entity.TokenEntity;
import com.parcel.authentication.entity.UserEntity;
import com.parcel.authentication.exception.UserCreateException;
import com.parcel.authentication.exception.UserNotFoundException;
import com.parcel.authentication.mapper.UserMapper;
import com.parcel.authentication.repository.UserRepository;
import com.parcel.authentication.service.TokenEntityService;
import com.parcel.authentication.service.UserService;
import com.parcel.authentication.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenEntityService tokenEntityService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt_access_token_expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${security.jwt_refresh_token_expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    @Override
    public UserSignUpResponseDto createUser(UserSignUpRequestDto dto) {
        if (userRepository.existsByUsernameOrPhone(dto.getUsername(), dto.getPhone())) {
            throw new UserCreateException("there is already user with this username or phone number");
        }
        UserEntity userEntity = userMapper.requestDtoToUserEntity(dto);
        return userMapper.entityToUserResponseDto(userRepository.save(userEntity));
    }

    @Override
    public UserAuthenticationResponseDto signIn(UserAuthenticationRequestDto dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurityDetailsDto userDetails = (UserSecurityDetailsDto) authentication.getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new UserNotFoundException("no user with this credentials"));
        String jwt = jwtUtil.generateJwtToken(userEntity);
        TokenEntity token = tokenEntityService.createTokenEntity(userEntity.getId());
        return UserAuthenticationResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(token.getToken())
                .accessExpirationInSeconds(ACCESS_TOKEN_EXPIRATION)
                .refreshExpirationInSeconds(REFRESH_TOKEN_EXPIRATION).build();
    }

    @Override
    public UserAuthenticationResponseDto refreshToken(TokenRequestDto dto) {
        TokenEntity token = tokenEntityService.verifyExpiration(dto);
        String accessToken = jwtUtil.generateJwtToken(userRepository.findById(token.getUserId()).orElseThrow(() ->
                new UserNotFoundException("no user with this id")));
        return UserAuthenticationResponseDto.builder().accessToken(accessToken).accessExpirationInSeconds(ACCESS_TOKEN_EXPIRATION).build();
    }
}
