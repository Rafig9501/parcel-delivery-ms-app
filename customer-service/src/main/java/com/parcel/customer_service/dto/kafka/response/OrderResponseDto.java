package com.parcel.customer_service.dto.kafka.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "destination")
    private String destination;

    @JsonProperty(value = "price")
    private String price;
}
