package com.koke.koke_backend.subscribe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

import static java.time.Duration.ofDays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Cycle {

    TWO_WEEK("2주", ofDays(14)),
    THREE_WEEK("3주", ofDays(21)),
    FOUR_WEEK("4주", ofDays(28)),
    FIVE_WEEK("5주", ofDays(35));

    private String description;
    private Duration duration;

}
