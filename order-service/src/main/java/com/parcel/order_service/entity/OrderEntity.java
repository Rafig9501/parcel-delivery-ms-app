package com.parcel.order_service.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "ORDER_", schema = "order_ms")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_")
    private Long id;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "STATUS_")
    private OrderStatus status;

    @Column(name = "DESTINATION_")
    private String destination;

    @Column(name = "PRICE_")
    private Double price;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "COURIER_ID")
    private Long courierId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(status, that.status) &&
                Objects.equals(destination, that.destination) && Objects.equals(price, that.price) && userId.equals(that.userId) &&
                courierId.equals(that.courierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, destination, price, userId, courierId);
    }
}
