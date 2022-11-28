package com.parcel.order_service.repository;

import com.parcel.order_service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserId(Long userId);

    List<OrderEntity> findAllByCourierId(Long courierId);

    Optional<OrderEntity> findByIdAndUserId(Long orderId, Long userId);

    Optional<OrderEntity> findByIdAndCourierId(Long id, Long courierId);
}
