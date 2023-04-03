package com.koke.koke_backend.order.service;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.order.dto.*;
import com.koke.koke_backend.order.entity.OrderInfo;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.order.entity.Subscribe;
import com.koke.koke_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderGateway orderGateway;


    @Transactional
    public ResponseEntity<ResponseMapper<Object>> createOrder(User current, OrderCreateRequestDto dto) {
        OrderInfo orderInfo = orderGateway.processFirstOrder(current);
        OrderProduct orderProduct = orderGateway.processOrderProduct(dto, orderInfo);

        return ResponseMapper.success();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<Object>> orderList(User current, OrderListRequestDto dto) {
        List<OrderListResponseDto> list = orderGateway.orderList(current, dto);
        return null;
    }

    @Transactional
    public ResponseEntity<ResponseMapper<Object>> createSubscribe(User current, SubscribeCreateRequestDto dto) {
        OrderInfo orderInfo = orderGateway.processFirstOrder(current);
        OrderProduct orderProduct = orderGateway.processOrderProduct(dto, orderInfo);
        Subscribe subscribe = orderGateway.processSubscribe(current, orderProduct, dto);

        return ResponseMapper.success(subscribe);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<Object>> subscribeList(User current) {
        List<SubscribeListResponseDto> list = orderGateway.subscribeList(current);
        return ResponseMapper.success(list);
    }

    @Transactional
    public ResponseEntity<ResponseMapper<Object>> cancelSubscribe(User current, String uuid) {
        Subscribe subscribe = orderGateway.cancelSubscribe(current, uuid);
        return ResponseMapper.success(subscribe);
    }
}
