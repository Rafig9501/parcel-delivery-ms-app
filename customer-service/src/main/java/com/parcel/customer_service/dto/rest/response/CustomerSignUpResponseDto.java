package com.parcel.customer_service.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSignUpResponseDto {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surname")
    private String surname;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "is_admin")
    private Boolean isAdmin;

    @JsonProperty(value = "date_time")
    private LocalDateTime dateTime;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "is_enabled")
    private Boolean isEnabled;

    @JsonProperty(value = "is_non_expired")
    private Boolean isNonExpired;
}
