package com.parcel.order_service.service;

import com.parcel.order_service.dto.request.OrderRequestDto;
import com.parcel.order_service.dto.response.OrderResponseDto;
import com.parcel.order_service.dto.response.OrderResponseListDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    OrderResponseDto create(OrderRequestDto dto);
    OrderResponseDto getById(Long orderId);
    Boolean deleteById(Long orderId);
    OrderResponseListDto getAllByUserId(Long userId);
    OrderResponseListDto getAllByCourierId(Long courierId);
    OrderResponseDto updateDestination(OrderRequestDto dto);
    OrderResponseDto updateStatus(OrderRequestDto dto);

    OrderResponseDto assignCourier(OrderRequestDto dto);
}
