package com.parcel.customer_service.dto.kafka.response;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderResponseListDto {

    private List<OrderResponseDto> orderResponseDtoList;
}
