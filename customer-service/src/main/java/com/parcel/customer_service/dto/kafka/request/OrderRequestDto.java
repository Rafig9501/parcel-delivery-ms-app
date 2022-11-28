package com.parcel.customer_service.dto.kafka.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
@EqualsAndHashCode
public class OrderRequestDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "courier_id")
    private Long courierId;

    @JsonProperty(value = "price")
    private Double price;

    @JsonProperty(value = "destination")
    private String destination;

    @JsonProperty(value = "status")
    private String status;
}
