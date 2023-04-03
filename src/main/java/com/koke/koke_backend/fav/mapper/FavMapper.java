package com.koke.koke_backend.fav.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.fav.entity.Fav;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.roastery.entity.Roastery;
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
public interface FavMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uuid", source = "user", qualifiedByName = "uuid")
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
	@Mapping(target = "product", ignore = true)
	@Mapping(target = "user", source = "user")
	@Mapping(target = "roastery", source = "roastery")
	@Mapping(target = "favType", constant = "ROASTERY")
	Fav createFavRoastery(User user, Roastery roastery);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uuid", source = "user", qualifiedByName = "uuid")
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
	@Mapping(target = "roastery", ignore = true)
	@Mapping(target = "user", source = "user")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "favType", constant = "PRODUCT")
	Fav createFavProduct(User user, Product product);
}
