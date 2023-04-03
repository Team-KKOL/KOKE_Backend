package com.koke.koke_backend.order.service;

import com.koke.koke_backend.order.dto.*;
import com.koke.koke_backend.order.entity.OrderInfo;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.order.entity.Subscribe;
import com.koke.koke_backend.order.mapper.OrderMapper;
import com.koke.koke_backend.order.mapper.OrderProductMapper;
import com.koke.koke_backend.order.mapper.SubscribeMapper;
import com.koke.koke_backend.order.repository.OrderProductRepository;
import com.koke.koke_backend.order.repository.OrderRepository;
import com.koke.koke_backend.order.repository.SubscribeRepository;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.koke.koke_backend.common.singleton.Constant.getNSEE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final SubscribeMapper subscribeMapper;

    @Transactional(isolation = REPEATABLE_READ)
    public OrderInfo processFirstOrder(User current) {
        OrderInfo orderInfo = orderMapper.createDtoToEntity(current);
        orderRepository.save(orderInfo);

        return orderInfo;
    }

    @Transactional(isolation = REPEATABLE_READ)
    public OrderProduct processOrderProduct(OrderCreateRequestDto dto, OrderInfo orderInfo) {
        OrderProduct orderProduct = orderProductMapper.orderCreateDtoToOrderProduct(dto, orderInfo);
        orderProductRepository.save(orderProduct);

        return orderProduct;
    }

    @Transactional(readOnly = true, isolation = REPEATABLE_READ)
    public List<OrderListResponseDto> orderList(User current, OrderListRequestDto dto) {
        return null;
    }

    @Transactional(isolation = REPEATABLE_READ)
    public Subscribe processSubscribe(User current, OrderProduct orderProduct, SubscribeCreateRequestDto dto) {
        Subscribe subscribe = subscribeMapper.orderProductToSubscribe(current, orderProduct, dto);
        subscribeRepository.save(subscribe);

        return subscribe;
    }

    @Transactional(readOnly = true, isolation = REPEATABLE_READ)
    public List<SubscribeListResponseDto> subscribeList(User current) {
        return subscribeRepository.list(current.getId());
    }

    @Transactional(isolation = REPEATABLE_READ)
    public Subscribe cancelSubscribe(User current, String uuid) {
        Subscribe subscribe = subscribeRepository.findByUuid(uuid).orElseThrow(getNSEE.apply("구독 정보"));
        subscribe.disable();

        return subscribeRepository.save(subscribe);
    }
}
