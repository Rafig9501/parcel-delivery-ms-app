package com.parcel.customer_service.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderListResponseDto {

    @JsonProperty(value = "orders")
    private List<CustomerOrderResponseDto> orders;
}
