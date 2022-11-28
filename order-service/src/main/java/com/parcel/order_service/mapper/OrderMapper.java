package com.parcel.order_service.mapper;

import com.parcel.order_service.dto.request.OrderRequestDto;
import com.parcel.order_service.dto.response.OrderResponseDto;
import com.parcel.order_service.entity.OrderEntity;
import com.parcel.order_service.entity.OrderStatus;
import com.parcel.order_service.mapper.qualifier.OrderMapperQualifier;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderMapperQualifier.class, OrderStatus.class, })
public interface OrderMapper {

    OrderResponseDto orderEntityToResponseDto(OrderEntity entity);

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusStringToEnum")
    @Mapping(target = "id", ignore = true)
    OrderEntity orderRequestDtoToEntity(OrderRequestDto dto);
}
