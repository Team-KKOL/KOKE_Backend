package com.koke.koke_backend.order.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.order.entity.OrderInfo;
import com.koke.koke_backend.user.entity.User;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {

	@Mapping(target = "user", source = "current")
	@Mapping(target = "uuid", source = "current", qualifiedByName = "uuid")
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
	OrderInfo createDtoToEntity(User current);


}
