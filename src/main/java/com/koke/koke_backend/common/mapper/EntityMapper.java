package com.koke.koke_backend.common.mapper;

import com.koke.koke_backend.product.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
		uses = {ReferenceMapper.class},
		builder = @Builder(disableBuilder = true),
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EntityMapper {

	Product stringToProduct(String productId);

}
