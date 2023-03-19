package com.koke.koke_backend.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GrindType {

    NONE(false, "그대로 주세요"), COPE_MAKER(true, "갈아주세요 - 커피 메이커"),
    ESPRESSO(true, "갈아주세요 - 에스프레소"), FRENCH_PRESS(true, "갈아주세요 - 프렌치 프레스"),
    HAND_DRIP(true, "갈아주세요 - 핸드 드립"), MOCHA_PORT(true, "갈아주세요 - 모카포트");

    private Boolean grindYn;

    private String hanNm;

}
