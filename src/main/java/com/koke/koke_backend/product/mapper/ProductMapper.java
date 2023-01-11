package com.koke.koke_backend.product.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
}
