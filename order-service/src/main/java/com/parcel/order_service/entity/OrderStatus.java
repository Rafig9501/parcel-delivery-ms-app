package com.parcel.order_service.entity;

public enum OrderStatus {

    WAITING("WAITING"), ASSIGNED("ASSIGNED"), ON_THE_WAY("ON_THE_WAY"), DELIVERED("DELIVERED");

    private final String status;

    OrderStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}
