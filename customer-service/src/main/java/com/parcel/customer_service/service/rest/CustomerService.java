package com.parcel.customer_service.service.rest;

import com.parcel.customer_service.dto.rest.request.CustomerOrderRequestDto;
import com.parcel.customer_service.dto.rest.request.CustomerSignUpRequestDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderListResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerSignUpResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerSignUpResponseDto signUpCustomer(CustomerSignUpRequestDto dto);

    CustomerOrderResponseDto getCustomerOrder(Long orderId, String jwtId);

    CustomerOrderResponseDto createCustomerOrder(CustomerOrderRequestDto dto);

    CustomerOrderResponseDto updateCustomerOrderDestination(CustomerOrderRequestDto dto, String jwtId);

    CustomerOrderListResponseDto getAllCustomerOrders(String customerId, String jwtId);

    Boolean deleteCustomerOrder(Long orderId, String jwtId);
}
