package com.koke.koke_backend.order.controller;

import com.koke.koke_backend.common.config.Uris;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.CurrentUser;
import com.koke.koke_backend.order.dto.OrderCreateRequestDto;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.service.OrderService;
import com.koke.koke_backend.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Tag(name = "주문 관리", description = "주문 관리 API")
    @Operation(summary = "주문 요청", description = "주문 요청 API")
    @SecurityRequirement(name = "TOKEN")
    @PostMapping(value = Uris.ORDER_ROOT)
    public ResponseEntity<ApiResponse<Object>> createOrder(@CurrentUser User current,
                                                           @Valid @RequestBody OrderCreateRequestDto dto) {
        return orderService.createOrder(current, dto);
    }

    @Tag(name = "주문 관리", description = "주문 관리 API")
    @Operation(summary = "주문 리스트 조회", description = "주문 리스트 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(value = Uris.ORDER_ROOT)
    public ResponseEntity<ApiResponse<Object>> orderList(@CurrentUser User current) {
        return orderService.orderList(current);
    }

    @Tag(name = "구독 관리", description = "구독 관리 API")
    @Operation(summary = "구독 신청", description = "구독 신청 API")
    @SecurityRequirement(name = "TOKEN")
    @PostMapping(value = Uris.SUBSCRIBE_ROOT)
    public ResponseEntity<ApiResponse<Object>> createSubscribe(@CurrentUser User current,
                                                              @Valid @RequestBody SubscribeCreateRequestDto dto) {
        return orderService.createSubscribe(current, dto);
    }

    @Tag(name = "구독 관리", description = "구독 관리 API")
    @Operation(summary = "구독 리스트 조회", description = "구독 리스트 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(value = Uris.SUBSCRIBE_ROOT)
    public ResponseEntity<ApiResponse<Object>> subscribeList(@CurrentUser User current) {
        return orderService.subscribeList(current);
    }

}
