package com.parcel.customer_service.service.rest.impl;

import com.parcel.customer_service.dto.rest.request.CustomerOrderRequestDto;
import com.parcel.customer_service.dto.rest.request.CustomerSignUpRequestDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderListResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerOrderResponseDto;
import com.parcel.customer_service.dto.rest.response.CustomerSignUpResponseDto;
import com.parcel.customer_service.entity.CustomerEntity;
import com.parcel.customer_service.exception.CustomerCreationException;
import com.parcel.customer_service.mapper.CustomerMapper;
import com.parcel.customer_service.repository.CustomerRepository;
import com.parcel.customer_service.service.rest.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerSignUpResponseDto signUpCustomer(CustomerSignUpRequestDto dto) {
        if (customerRepository.existsByUsernameOrPhone(dto.getUsername(), dto.getPhone())) {
            throw new CustomerCreationException("there is already user with this username or phone number");
        }
        CustomerEntity customerEntity = customerMapper.requestCustomerDtoToCustomerEntity(dto);
        return customerMapper.responseCustomerDtoToCustomerEntity(customerRepository.save(customerEntity));
    }

    @Override
    public CustomerOrderResponseDto getCustomerOrder(Long orderId, String jwtId) {
        return null;
    }

    @Override
    public CustomerOrderResponseDto createCustomerOrder(CustomerOrderRequestDto dto) {
        return null;
    }

    @Override
    public CustomerOrderResponseDto updateCustomerOrderDestination(CustomerOrderRequestDto dto, String jwtId) {
        return null;
    }

    @Override
    public CustomerOrderListResponseDto getAllCustomerOrders(String customerId, String jwtId) {
        return null;
    }

    @Override
    public Boolean deleteCustomerOrder(Long orderId, String jwtId) {
        return null;
    }
}
