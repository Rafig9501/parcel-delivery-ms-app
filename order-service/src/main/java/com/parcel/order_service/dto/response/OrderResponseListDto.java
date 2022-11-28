package com.parcel.order_service.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseListDto {

    private List<OrderResponseDto> orderResponseDtoList;
}
