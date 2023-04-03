package com.koke.koke_backend.product.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.product.dto.json.ProductDataDto;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.product.mapper.decorator.ProductDecorator;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(ProductDecorator.class)
public interface ProductMapper {

	@Named("toEntity")
	@Mapping(target = "uuid", source = "dto", qualifiedByName = "uuid")
	@Mapping(target = "description", source = "summary")
	@Mapping(target = "roastery", ignore = true)
	@Mapping(target = "weight", ignore = true)
	Product toEntity(ProductDataDto dto);

	@IterableMapping(qualifiedByName = "toEntity")
    List<Product> toEntityList(List<ProductDataDto> productDataDtos);

	@Named("uuid")
	default String uuid(ProductDataDto dto) {
		return UUID.randomUUID().toString();
	}

}
