package com.parcel.customer_service.dto.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.customer_service.util.validation_groups.CustomerCreateOrder;
import com.parcel.customer_service.util.validation_groups.CustomerUpdateOrder;
import lombok.*;

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
public class CustomerOrderRequestDto {

    @NotNull(groups = {CustomerUpdateOrder.class}, message = "provide order id for updating")
    @Min(value = 1, groups = {CustomerUpdateOrder.class}, message = "order id must be natural number")
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "name cannot be null or empty", groups = CustomerCreateOrder.class)
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message = "Customer id cannot be null")
    @Min(value = 1, groups = {CustomerCreateOrder.class, CustomerUpdateOrder.class},
            message = "Customer id must be natural number")
    @JsonProperty(value = "Customer_id")
    private Long customerId;

    @DecimalMin(value = "1.0", message = "price must be double number", groups = CustomerCreateOrder.class)
    @JsonProperty(value = "price")
    private Double price;

    @NotBlank(message = "destination cannot be null or empty", groups = {CustomerCreateOrder.class, CustomerUpdateOrder.class})
    @JsonProperty(value = "destination")
    private String destination;
}
