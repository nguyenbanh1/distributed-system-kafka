package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.entity.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "address", source = "address")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "userId", source = "userId")
    Order of(OrderRequest orderReq);
}
