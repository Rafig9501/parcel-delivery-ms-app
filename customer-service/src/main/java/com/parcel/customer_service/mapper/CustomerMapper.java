package com.parcel.customer_service.mapper;

import com.parcel.customer_service.dto.rest.request.CustomerSignUpRequestDto;
import com.parcel.customer_service.dto.rest.response.CustomerSignUpResponseDto;
import com.parcel.customer_service.entity.CustomerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isNonExpired", expression = "java(Boolean.TRUE)")
    @Mapping(target = "isEnabled", expression = "java(Boolean.TRUE)")
    @Mapping(target = "password", ignore = true)
    CustomerEntity requestCustomerDtoToCustomerEntity(CustomerSignUpRequestDto dto);

    CustomerSignUpResponseDto responseCustomerDtoToCustomerEntity(CustomerEntity customerEntity);
}
