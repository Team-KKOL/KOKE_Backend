package com.koke.koke_backend.order.controller;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.common.config.Uris;
import com.koke.koke_backend.common.security.CurrentUser;
import com.koke.koke_backend.order.dto.OrderCreateRequestDto;
import com.koke.koke_backend.order.dto.OrderListRequestDto;
import com.koke.koke_backend.order.dto.SubscribeCreateRequestDto;
import com.koke.koke_backend.order.service.OrderService;
import com.koke.koke_backend.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Tag(name = "주문 관리", description = "주문 관리 API")
    @Operation(summary = "주문 요청", description = "주문 요청 API")
    @SecurityRequirement(name = "TOKEN")
    @PostMapping(value = Uris.ORDER_ROOT)
    public ResponseEntity<ResponseMapper<Object>> createOrder(@CurrentUser User current,
                                                              @Valid @RequestBody OrderCreateRequestDto dto) {
        return orderService.createOrder(current, dto);
    }

    @Tag(name = "주문 관리", description = "주문 관리 API")
    @Operation(summary = "주문 리스트 조회", description = "주문 리스트 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(value = Uris.ORDER_ROOT)
    public ResponseEntity<ResponseMapper<Object>> orderList(@CurrentUser User current, @Valid @RequestBody OrderListRequestDto dto) {
        return orderService.orderList(current, dto);
    }

    @Tag(name = "구독 관리", description = "구독 관리 API")
    @Operation(summary = "구독 신청", description = "구독 신청 API")
    @SecurityRequirement(name = "TOKEN")
    @PostMapping(value = Uris.SUBSCRIBE_ROOT)
    public ResponseEntity<ResponseMapper<Object>> createSubscribe(@CurrentUser User current,
                                                                  @Valid @RequestBody SubscribeCreateRequestDto dto) {
        return orderService.createSubscribe(current, dto);
    }

    @Tag(name = "구독 관리", description = "구독 관리 API")
    @Operation(summary = "구독 리스트 조회", description = "구독 리스트 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(value = Uris.SUBSCRIBE_ROOT)
    public ResponseEntity<ResponseMapper<Object>> subscribeList(@CurrentUser User current) {
        return orderService.subscribeList(current);
    }

    @Tag(name = "구독 관리", description = "구독 관리 API")
    @Operation(summary = "구독 해제", description = "구독 해제 API")
    @SecurityRequirement(name = "TOKEN")
    @DeleteMapping(value = Uris.SUBSCRIBE_ROOT + Uris.REST_NAME_UUID)
    public ResponseEntity<ResponseMapper<Object>> cancelSubscribe(@CurrentUser User current, @PathVariable String uuid) {
        return orderService.cancelSubscribe(current, uuid);
    }

}
