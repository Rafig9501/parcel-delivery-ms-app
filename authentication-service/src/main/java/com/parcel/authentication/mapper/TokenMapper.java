package com.parcel.authentication.mapper;

import com.parcel.authentication.dto.request.TokenRequestDto;
import com.parcel.authentication.entity.TokenEntity;
import com.parcel.authentication.mapper.qualifier.UserMapperQualifier;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapperQualifier.class})
public interface TokenMapper {

    TokenEntity dtoToTokenEntity(TokenRequestDto dto);
}
