package com.parcel.order_service.mapper.qualifier;

import com.parcel.order_service.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapperQualifier {

    @Named(value = "mapStatusStringToEnum")
    public OrderStatus statusStringToEnum(String status){
        return OrderStatus.valueOf(status);
    }
}
