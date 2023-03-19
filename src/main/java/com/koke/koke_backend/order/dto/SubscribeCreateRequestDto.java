package com.koke.koke_backend.order.dto;

import com.koke.koke_backend.order.enums.Cycle;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeCreateRequestDto extends OrderCreateRequestDto {

    @NotNull(message = "구독 주기를 입력하지 않으셨습니다.")
    private Cycle cycle;

}
