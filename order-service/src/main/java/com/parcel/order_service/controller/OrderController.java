package com.parcel.order_service.controller;

import com.parcel.order_service.dto.request.OrderRequestDto;
import com.parcel.order_service.dto.response.OrderResponseDto;
import com.parcel.order_service.dto.response.OrderResponseListDto;
import com.parcel.order_service.service.OrderService;
import com.parcel.order_service.util.validation_groups.CourierAssignOrder;
import com.parcel.order_service.util.validation_groups.StatusUpdateOrder;
import com.parcel.order_service.util.validation_groups.UserCreateOrder;
import com.parcel.order_service.util.validation_groups.UserUpdateOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Get an Order", notes = "Get order details by order id", response = OrderResponseDto.class)
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> getOrderDetailsById(@RequestParam(name = "order_id") @Min(1) Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @ApiOperation(value = "Get all user's Orders", notes = "Get all user's orders by user id", response = OrderResponseListDto.class)
    @GetMapping(path = "/get-all-user-orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseListDto> getOrdersByUserId(@RequestParam(name = "user_id") @Min(1) Long id) {
        return ResponseEntity.ok(orderService.getAllByUserId(id));
    }

    @ApiOperation(value = "Get all courier's Orders", notes = "Get all courier's Orders", response = OrderResponseListDto.class)
    @GetMapping(value = "/get-all-courier-orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseListDto> getOrdersByCourierId(@RequestParam(name = "courier_id") @Min(1) Long id){
        return ResponseEntity.ok(orderService.getAllByCourierId(id));
    }

    @ApiOperation(value = "Create order", notes = "Create an order", response = OrderResponseDto.class)
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Validated(UserCreateOrder.class) OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.create(dto));
    }

    @ApiOperation(value = "Cancel order", notes = "Cancel order by order id", response = Boolean.class)
    @DeleteMapping(path = "/cancel")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam(name = "order_id") Long id) {
        return ResponseEntity.ok(orderService.deleteById(id));
    }

    @ApiOperation(value = "Update order's destination", notes = "Update order's destination", response = OrderResponseDto.class)
    @PatchMapping(path = "/update-destination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> updateOrderDestination(@RequestBody @Validated(UserUpdateOrder.class) OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.updateDestination(dto));
    }

    @ApiOperation(value = "Assign order's courier", notes = "Assign order's courier", response = OrderResponseDto.class)
    @PatchMapping(path = "/assign-courier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> assignOrderCourier(@RequestBody @Validated(CourierAssignOrder.class) OrderRequestDto dto){
        return ResponseEntity.ok(orderService.assignCourier(dto));
    }

    @ApiOperation(value = "Update order's status", notes = "Update order's status", response = OrderResponseDto.class)
    @PatchMapping(path = "/update-status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestBody @Validated(StatusUpdateOrder.class) OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.updateStatus(dto));
    }
}
