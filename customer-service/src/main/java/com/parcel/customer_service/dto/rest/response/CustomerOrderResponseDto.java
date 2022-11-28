package com.parcel.customer_service.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderResponseDto {

    @JsonProperty(value = "customer_id")
    private UUID customerId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surname")
    private String surname;

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "creation_date")
    private LocalDateTime creationDate;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "is_non_expired")
    private String isNonExpired;

    @JsonProperty(value = "is_enabled")
    private String isEnabled;
}
