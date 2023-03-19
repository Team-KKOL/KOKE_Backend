package com.koke.koke_backend.order.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.order.entity.OrderInfo;
import com.koke.koke_backend.user.entity.User;
import org.mapstruct.*;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {

	@Mapping(target = "id", source = "current", qualifiedByName = "generateOrderId")
	@Mapping(target = "user", source = "current")
    @Mapping(target = "orderProducts", ignore = true)
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
	OrderInfo createDtoToEntity(User current);

	@Named("generateOrderId")
	default String generateOrderId(User current) {
		return now().format(ofPattern("yyyyMMddHHmmss")) + randomAlphanumeric(5);
	}

}
