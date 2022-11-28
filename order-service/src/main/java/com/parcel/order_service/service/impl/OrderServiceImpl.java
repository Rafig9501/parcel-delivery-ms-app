package com.parcel.order_service.service.impl;

import com.parcel.order_service.dto.request.OrderRequestDto;
import com.parcel.order_service.dto.response.OrderResponseDto;
import com.parcel.order_service.dto.response.OrderResponseListDto;
import com.parcel.order_service.entity.OrderEntity;
import com.parcel.order_service.exception.OrderNotFoundException;
import com.parcel.order_service.mapper.OrderMapper;
import com.parcel.order_service.repository.OrderRepository;
import com.parcel.order_service.service.OrderService;
import com.parcel.order_service.util.enum_converter.StatusConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final StatusConverter statusConverter;

    @Override
    public OrderResponseDto create(OrderRequestDto dto) {
        log.info("creating an order for user id={}", dto.getUserId());
        dto.setStatus("WAITING");
        return orderMapper.orderEntityToResponseDto(orderRepository.save(orderMapper.orderRequestDtoToEntity(dto)));
    }

    @Override
    public OrderResponseDto getById(Long orderId) {
        log.info("getting order from db by id = {}", orderId);
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            log.error("no order found for id = {}", orderId);
            throw new OrderNotFoundException("no order with this id = " + orderId);
        }
        return orderMapper.orderEntityToResponseDto(order.get());
    }

    @Override
    public Boolean deleteById(Long orderId) {
        log.info("deleting order by id = {}", orderId);
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            return Boolean.FALSE;
        }
        orderRepository.delete(order.get());
        return Boolean.TRUE;
    }

    @Override
    public OrderResponseListDto getAllByUserId(Long userId) {
        log.info("getting all orders from db by user id = {}", userId);
        List<OrderEntity> orders = orderRepository.findAllByUserId(userId);
        if (orders.isEmpty()) {
            log.error("no orders found for user id = {}", userId);
            throw new OrderNotFoundException("no orders found for user id = " + userId);
        }
        return OrderResponseListDto.builder().orderResponseDtoList(orders.stream().map(orderMapper::orderEntityToResponseDto)
                .collect(Collectors.toList())).build();
    }

    @Override
    public OrderResponseListDto getAllByCourierId(Long courierId) {
        log.info("getting all orders from db by courier id={}", courierId);
        List<OrderEntity> orders = orderRepository.findAllByCourierId(courierId);
        if (orders.isEmpty()){
            log.error("no orders found for courier id={}", courierId);
            throw new OrderNotFoundException("no orders found for courier id=" + courierId);
        }
        return OrderResponseListDto.builder().orderResponseDtoList(orders.stream().map(orderMapper::orderEntityToResponseDto)
                .collect(Collectors.toList())).build();
    }

    @Override
    public OrderResponseDto updateDestination(OrderRequestDto dto) {
        log.info("finding order by user id={} and order id={}", dto.getUserId(), dto.getId());
        Optional<OrderEntity> order = orderRepository.findByIdAndUserId(dto.getId(), dto.getUserId());
        if (order.isEmpty()){
            log.error("there is no order for user id={} and orderId={}", dto.getUserId(), dto.getId());
            throw new OrderNotFoundException("there is no order for user id=" + dto.getUserId() + " and orderId=" + dto.getId());
        }
        OrderEntity orderEntity = order.get();
        orderEntity.setDestination(dto.getDestination());
        return orderMapper.orderEntityToResponseDto(orderRepository.save(orderEntity));

    }

    @Override
    public OrderResponseDto updateStatus(OrderRequestDto dto) {
        log.info("finding order by courier id={} and order id={}", dto.getCourierId(), dto.getId());
        Optional<OrderEntity> order = orderRepository.findByIdAndCourierId(dto.getId(), dto.getCourierId());
        if (order.isEmpty()){
            log.error("there is no order for courier id={} and orderId={}", dto.getCourierId(), dto.getId());
            throw new OrderNotFoundException("there is no order for courier id=" + dto.getCourierId() + " and orderId=" + dto.getId());
        }
        OrderEntity orderEntity = order.get();
        orderEntity.setStatus(statusConverter.convertToEntityAttribute(dto.getStatus()));
        return orderMapper.orderEntityToResponseDto(orderRepository.save(orderEntity));
    }

    @Override
    public OrderResponseDto assignCourier(OrderRequestDto dto) {
        log.info("finding order by order id={}", dto.getId());
        Optional<OrderEntity> order = orderRepository.findById(dto.getId());
        if (order.isEmpty()) {
            log.error("no order found for id = {}", dto.getId());
            throw new OrderNotFoundException("no order with this id = " + dto.getId());
        }
        OrderEntity orderEntity = order.get();
        orderEntity.setCourierId(dto.getCourierId());
        return orderMapper.orderEntityToResponseDto(orderRepository.save(orderEntity));
    }
}
