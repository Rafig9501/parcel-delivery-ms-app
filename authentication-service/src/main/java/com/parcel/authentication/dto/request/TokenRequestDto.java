package com.parcel.authentication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {

    @JsonProperty(value = "refresh_token")
    @NotBlank(message = "token cannot be null or empty")
    private String refreshToken;
}
