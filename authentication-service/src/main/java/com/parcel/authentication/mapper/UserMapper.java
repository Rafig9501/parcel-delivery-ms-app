package com.parcel.authentication.mapper;

import com.parcel.authentication.dto.request.UserSignUpRequestDto;
import com.parcel.authentication.dto.response.UserSignUpResponseDto;
import com.parcel.authentication.entity.UserEntity;
import com.parcel.authentication.mapper.qualifier.UserMapperQualifier;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapperQualifier.class})
public interface UserMapper {

    UserSignUpResponseDto entityToUserResponseDto(UserEntity entity);

    @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isNonExpired", expression = "java(Boolean.TRUE)")
    @Mapping(target = "isNonLocked", expression = "java(Boolean.TRUE)")
    @Mapping(target = "isEnabled", expression = "java(Boolean.TRUE)")
    @Mapping(target = "password", source = "password", qualifiedByName = "hashPassword")
    UserEntity requestDtoToUserEntity(UserSignUpRequestDto dto);
}
