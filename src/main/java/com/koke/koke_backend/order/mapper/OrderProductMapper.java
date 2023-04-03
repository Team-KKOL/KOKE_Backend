package com.koke.koke_backend.order.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.order.dto.OrderCreateRequestDto;
import com.koke.koke_backend.order.entity.OrderInfo;
import com.koke.koke_backend.order.entity.OrderProduct;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderProductMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uuid", source = "dto", qualifiedByName = "uuid")
	@Mapping(target = "review", ignore = true)
	@Mapping(target = "orderStatus", constant = "ORDERED")
	@Mapping(target = "volume", source = "dto.volume")
	@Mapping(target = "grind", source = "dto.grind")
	@Mapping(target = "product", source = "dto.productId")
	@Mapping(target = "orderInfo", source = "orderInfo")
	OrderProduct orderCreateDtoToOrderProduct(OrderCreateRequestDto dto, OrderInfo orderInfo);

}
