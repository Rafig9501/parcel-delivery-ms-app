package com.parcel.authentication.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationRequestDto {

    @NotBlank(message = "username cannot be null or empty")
    @Length(min = 5, max = 16, message = "username must be at least 5 characters and at most 16")
    private String username;

    @NotBlank(message = "password cannot be null or empty")
    @Length(min = 8, max = 30, message = "password must be at least 8 characters and at most 30")
    private String password;
}
