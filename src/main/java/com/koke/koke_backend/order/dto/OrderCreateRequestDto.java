package com.koke.koke_backend.order.dto;

import com.koke.koke_backend.order.enums.GrindType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequestDto {
    @NotBlank(message = "상품 ID를 입력하지 않으셨습니다.")
    @Size(max = 30, message = "상품 ID는 30자 이내로 입력해주세요.")
    private String productId; // 상품 id

    @NotBlank(message = "원두 용량을 입력하지 않으셨습니다.")
    @Size(max = 30, message = "상품 ID는 30자 이내로 입력해주세요.")
    private String volume; // 원두 용량

    @NotNull(message = "원두 분쇄 여부를 입력하지 않으셨습니다.")
    private GrindType grind; // 원두 분쇄 여부
}
