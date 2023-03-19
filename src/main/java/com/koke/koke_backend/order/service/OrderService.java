package com.koke.koke_backend.order.service;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.order.dto.OrderCreateRequestDto;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.dto.SubscribeListResponseDto;
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
    public ResponseEntity<ApiResponse<Object>> createOrder(User current, OrderCreateRequestDto dto) {
        OrderInfo orderInfo = orderGateway.processFirstOrder(current);
        OrderProduct orderProduct = orderGateway.processOrderProduct(dto, orderInfo);

        return ApiResponse.success();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Object>> orderList(User current) {

        return null;
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> createSubscribe(User current, SubscribeCreateRequestDto dto) {
        OrderInfo orderInfo = orderGateway.processFirstOrder(current);
        OrderProduct orderProduct = orderGateway.processOrderProduct(dto, orderInfo);
        Subscribe subscribe = orderGateway.processSubscribe(current, orderProduct, dto);

        return ApiResponse.success();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Object>> subscribeList(User current) {
        List<SubscribeListResponseDto> list = orderGateway.subscribeList(current);
        return ApiResponse.success(list);
    }
}
