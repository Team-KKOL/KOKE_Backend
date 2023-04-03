package com.koke.koke_backend.order.mapper.decorator;

import com.koke.koke_backend.application.utils.LocalDateUtils;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.order.entity.Subscribe;
import com.koke.koke_backend.order.mapper.SubscribeMapper;
import com.koke.koke_backend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;

@Primary
public class SubscribeDecorator implements SubscribeMapper {

    @Autowired
    @Qualifier("delegate")
    private SubscribeMapper delegate;

    @Override
    public Subscribe orderProductToSubscribe(User current, OrderProduct orderProduct, SubscribeCreateRequestDto dto) {
        Subscribe subscribe = delegate.orderProductToSubscribe(current, orderProduct, dto);

        LocalDate nowDate = now().toLocalDate();
        return subscribe.toBuilder()
                .subscribeDt(nowDate)
                .deliveryDt(LocalDateUtils.calculateFirstDeliveryDt(nowDate))
                .build();
    }
}
