package com.koke.koke_backend.order.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.order.entity.Subscribe;
import com.koke.koke_backend.order.mapper.decorator.SubscribeDecorator;
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
@DecoratedWith(SubscribeDecorator.class)
public interface SubscribeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "current", qualifiedByName = "uuid")
    @Mapping(target = "user", source = "current")
    @Mapping(target = "orderProduct", source = "orderProduct")
    @Mapping(target = "cycle", source = "dto.cycle")
    @Mapping(target = "insDtm", ignore = true)
    @Mapping(target = "updDtm", ignore = true)
    @Mapping(target = "deliveryDt", ignore = true)
    @Mapping(target = "subscribeDt", ignore = true)
    Subscribe orderProductToSubscribe(User current, OrderProduct orderProduct, SubscribeCreateRequestDto dto);

}
