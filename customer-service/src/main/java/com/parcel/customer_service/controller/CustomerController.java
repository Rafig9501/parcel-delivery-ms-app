package com.parcel.customer_service.controller;

import com.parcel.customer_service.dto.rest.request.CustomerOrderRequestDto;
import com.parcel.customer_service.dto.rest.request.CustomerSignUpRequestDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderListResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerSignUpResponseDto;
import com.parcel.customer_service.service.rest.CustomerService;
import com.parcel.customer_service.util.validation_groups.CustomerCreateOrder;
import com.parcel.customer_service.util.validation_groups.CustomerUpdateOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @ApiOperation(value = "Get Customer Order", notes = "Get Customer Order by Order Id", response = CustomerOrderResponseDto.class)
    @GetMapping(path = "/get-order-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderResponseDto> getCustomerOrderById(@RequestParam Long orderId, @RequestHeader(value = "id") String jwtId) {
        return ResponseEntity.ok(customerService.getCustomerOrder(orderId, jwtId));
    }

    @ApiOperation(value = "Get all Customer's Orders", notes = "Get all Customer's Orders by Customer id", response = CustomerOrderListResponseDto.class)
    @GetMapping(path = "/get-all-orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderListResponseDto> getCustomerOrderListById(@RequestParam String customerId, @RequestHeader(value = "id") String jwtId) {
        return ResponseEntity.ok(customerService.getAllCustomerOrders(customerId, jwtId));
    }

    @ApiOperation(value = "Sign up", notes = "Sign up with a customer", response = CustomerSignUpResponseDto.class)
    @PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerSignUpResponseDto> signUpCustomer(@RequestBody CustomerSignUpRequestDto dto) {
        return ResponseEntity.ok(customerService.signUpCustomer(dto));
    }

    @ApiOperation(value = "Create Customer Order", notes = "Create an order for customer", response = CustomerOrderResponseDto.class)
    @PostMapping(path = "/create-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderResponseDto> createCustomerOrder(@RequestBody @Validated(CustomerCreateOrder.class) CustomerOrderRequestDto dto) {
        return ResponseEntity.ok(customerService.createCustomerOrder(dto));
    }

    @ApiOperation(value = "Update Order's Destination", notes = "Update customer order's destination", response = CustomerOrderResponseDto.class)
    @PatchMapping(path = "/update-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderResponseDto> updateCustomerOrderDestination(@RequestBody @Validated(CustomerUpdateOrder.class) CustomerOrderRequestDto dto,
                                                                                   @RequestHeader String jwtId) {
        return ResponseEntity.ok(customerService.updateCustomerOrderDestination(dto, jwtId));
    }

    @ApiOperation(value = "Cancel order", notes = "Delete customer's order", response = Boolean.class)
    @DeleteMapping(path = "/delete-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteCustomerOrder(@RequestParam Long orderId, @RequestHeader(value = "id") String jwtId) {
        return ResponseEntity.ok(customerService.deleteCustomerOrder(orderId, jwtId));
    }
}
