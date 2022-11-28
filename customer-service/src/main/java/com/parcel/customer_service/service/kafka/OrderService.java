package com.parcel.customer_service.service.kafka;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Message<?> getCustomerOrderByOrderId();
}
