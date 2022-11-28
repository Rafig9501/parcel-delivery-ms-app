package com.parcel.authentication.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponseDto {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "access_expiration_in_seconds")
    private Long accessExpirationInSeconds;

    @JsonProperty(value = "refresh_expiration_in_seconds")
    private Long refreshExpirationInSeconds;
}
