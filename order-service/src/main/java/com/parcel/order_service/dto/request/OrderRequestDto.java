package com.parcel.order_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.order_service.entity.OrderStatus;
import com.parcel.order_service.util.enum_validation.ValueOfEnum;
import com.parcel.order_service.util.validation_groups.CourierAssignOrder;
import com.parcel.order_service.util.validation_groups.StatusUpdateOrder;
import com.parcel.order_service.util.validation_groups.UserCreateOrder;
import com.parcel.order_service.util.validation_groups.UserUpdateOrder;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
@EqualsAndHashCode
public class OrderRequestDto {

    @NotNull(groups = {CourierAssignOrder.class, UserUpdateOrder.class, StatusUpdateOrder.class}, message = "provide order id for updating")
    @Min(value = 1, groups = {CourierAssignOrder.class, UserUpdateOrder.class, StatusUpdateOrder.class}, message = "order id must be natural number")
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "name cannot be null or empty", groups = UserCreateOrder.class)
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message = "user id cannot be null")
    @Min(value = 1, groups = {UserCreateOrder.class, UserUpdateOrder.class}, message = "user id must be natural number")
    @JsonProperty(value = "user_id")
    private Long userId;

    @Min(value = 1, message = "courier id must be natural number", groups = StatusUpdateOrder.class)
    @JsonProperty(value = "courier_id")
    private Long courierId;

    @DecimalMin(value = "1.0", message = "price must be double number", groups = UserCreateOrder.class)
    @JsonProperty(value = "price")
    private Double price;

    @NotBlank(message = "destination cannot be null or empty", groups = {UserCreateOrder.class, UserUpdateOrder.class})
    @JsonProperty(value = "destination")
    private String destination;

    @ValueOfEnum(enumClass = OrderStatus.class, message = "status can be one of values - WAITING, ASSIGNED, ON_THE_WAY, DELIVERED",
            groups = StatusUpdateOrder.class)
    @JsonProperty(value = "status")
    private String status;
}
