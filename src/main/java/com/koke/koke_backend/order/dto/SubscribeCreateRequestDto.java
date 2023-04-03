package com.koke.koke_backend.order.dto;

import com.koke.koke_backend.common.validation.annotation.ValidEnum;
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

    @ValidEnum(message = "다음 값만 입력 가능합니다. ['TWO_WEEK', 'THREE_WEEK', 'ONE_MONTH', 'FIVE_WEEK']", enumClass = Cycle.class)
    @NotNull(message = "구독 주기를 입력하지 않으셨습니다.")
    private Cycle cycle;

}
