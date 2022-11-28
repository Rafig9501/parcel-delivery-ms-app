package com.parcel.customer_service.util.constants;

public class KafkaConstants {

    public final static String PRODUCER_TYPE_MAPPINGS = "orderRequestDto:com.parcel.customer_service.dto.kafka.request.OrderRequestDto";

    public final static String CONSUMER_TYPE_MAPPINGS = "orderResponseDto:com.parcel.customer_service.dto.kafka.response.OrderResponseDto," +
                                                        "orderResponseListDto:com.parcel.customer_service.dto.kafka.response.OrderResponseListDto";
}
