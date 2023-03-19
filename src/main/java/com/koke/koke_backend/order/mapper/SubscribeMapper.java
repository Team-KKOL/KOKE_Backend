package com.koke.koke_backend.order.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.order.entity.Subscribe;
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
public interface SubscribeMapper {

    @Mapping(target = "subscribeId", source = "current", qualifiedByName = "generateSubscribeId")
    @Mapping(target = "user", source = "current")
    @Mapping(target = "orderProduct", source = "orderProduct")
    @Mapping(target = "cycle", source = "dto.cycle")
	@Mapping(target = "insDtm", ignore = true)
	@Mapping(target = "updDtm", ignore = true)
    Subscribe orderProductToSubscribe(User current, OrderProduct orderProduct, SubscribeCreateRequestDto dto);

	@Named("generateSubscribeId")
	default String generateOrderId(User current) {
		return now().format(ofPattern("yyyyMMddHHmmss")) + randomAlphanumeric(5);
	}

}
