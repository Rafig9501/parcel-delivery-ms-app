package com.parcel.authentication.controller;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.dto.request.UserAuthenticationRequestDto;
import com.parcel.authentication.dto.request.UserSignUpRequestDto;
import com.parcel.authentication.dto.response.UserAuthenticationResponseDto;
import com.parcel.authentication.dto.response.UserSignUpResponseDto;
import com.parcel.authentication.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final UserServiceImpl userService;

    @ApiOperation(value = "Sign in", notes = "Sign in with username and password", response = UserAuthenticationResponseDto.class)
    @PostMapping(path = "/user-sign-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthenticationResponseDto> signIn(@RequestBody @Valid UserAuthenticationRequestDto dto) {
        return ResponseEntity.ok(userService.signIn(dto));
    }

    @ApiOperation(value = "Refresh tokens", notes = "Refresh tokens and get new ones", response = UserAuthenticationResponseDto.class)
    @PostMapping(path = "/refresh-tokens", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthenticationResponseDto> refreshTokens(@RequestBody @Valid TokenRequestDto dto) {
        return ResponseEntity.ok(userService.refreshToken(dto));
    }

    @ApiOperation(value = "Sign up", notes = "Sign up user", response = UserSignUpResponseDto.class)
    @PostMapping(path = "/user-sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSignUpResponseDto> signUp(@RequestBody @Valid UserSignUpRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    } -d
}
