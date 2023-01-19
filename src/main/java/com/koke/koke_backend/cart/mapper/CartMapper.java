package com.koke.koke_backend.cart.mapper;

import com.koke.koke_backend.cart.entity.Cart;
import com.koke.koke_backend.common.mapper.EntityMapper;
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
public interface CartMapper {
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
	@Mapping(target = "cartProducts", ignore = true)
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
	Cart createCart(User user);
}
