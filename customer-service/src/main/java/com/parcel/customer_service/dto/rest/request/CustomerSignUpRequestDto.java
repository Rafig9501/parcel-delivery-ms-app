package com.parcel.customer_service.dto.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.customer_service.util.password_validation.ValidPassword;
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
public class CustomerSignUpRequestDto {

    @JsonProperty(value = "name")
    @NotBlank(message = "name cannot be null or empty")
    private String name;

    @JsonProperty(value = "surname")
    @NotBlank(message = "surname cannot be null or empty")
    private String surname;

    @JsonProperty(value = "username")
    @NotBlank(message = "username cannot be null or empty")
    private String username;

    @JsonProperty(value = "password")
    @ValidPassword(message = "password must have at least 1 character, 1 Upper Letter, 1 Digit and no whitespace")
    private String password;

    @JsonProperty(value = "phone")
    @NotBlank(message = "phone cannot be null or empty")
    private String phone;
}
