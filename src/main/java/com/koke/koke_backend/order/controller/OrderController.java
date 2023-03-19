package com.koke.koke_backend.order.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.CurrentUser;
import com.koke.koke_backend.order.dto.OrderCreateRequestDto;
import com.koke.koke_backend.order.service.OrderService;
import com.koke.koke_backend.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 관리", description = "주문 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Tag(name = "주문 관리", description = "주문 관리 API")
    @Operation(summary = "주문 요청", description = "주문 요청 API")
    @SecurityRequirement(name = "TOKEN")
    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@CurrentUser User current,
                                                 @Valid @RequestBody OrderCreateRequestDto dto) {

        return null;

    }

}
