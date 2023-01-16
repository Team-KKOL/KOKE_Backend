package com.koke.koke_backend.user.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.mapper.decorator.UserDecorator;
import org.mapstruct.*;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(UserDecorator.class)
public interface UserMapper {

	@Mapping(target = "role", constant = "USER")
	@Mapping(target = "password", ignore = true)
	User signUpRequestDtoToUser(SignUpRequestDto signUpRequestDto);

}
