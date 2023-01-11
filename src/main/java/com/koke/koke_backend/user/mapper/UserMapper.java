package com.koke.koke_backend.user.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.common.security.oauth.info.OAuth2UserInfo;
import com.koke.koke_backend.common.security.oauth.info.ProviderType;
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
public interface UserMapper {


	@Mapping(target = "userSeq", ignore = true)
	@Mapping(target = "userId", source = "oAuth2UserInfo.id")
    @Mapping(target = "username", source = "oAuth2UserInfo.name")
	@Mapping(target = "password", constant = "NO_PASSWORD")
	@Mapping(target = "profileImageUrl", source = "oAuth2UserInfo.imageUrl")
	@Mapping(target = "emailVerifiedYn", constant = "Y")
	@Mapping(target = "role", constant = "USER")
	User toEntity(OAuth2UserInfo oAuth2UserInfo, ProviderType providerType);

}
