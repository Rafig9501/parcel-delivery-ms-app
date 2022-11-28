package com.parcel.authentication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.authentication.util.password_validation.ValidPassword;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserSignUpRequestDto {

    @JsonProperty(value = "username")
    @NotBlank(message = "username cannot be null or empty")
    private String username;

    @JsonProperty(value = "role")
    @NotBlank(message = "role cannot be null or empty")
    private String role;

    @JsonProperty(value = "password")
    @ValidPassword(message = "password must have at least 1 character, 1 Upper Letter, 1 Digit and no whitespace")
    private String password;

    @JsonProperty(value = "phone")
    private String phone;
}
