package com.koke.koke_backend.order.dto;

import com.koke.koke_backend.order.enums.GrindType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequestDto {
    private String productId; // 상품 id
    private String volume; // 원두 용량
    private GrindType grind; // 원두 분쇄 여부
}
