package com.koke.koke_backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListRequestDto {

    private LocalDate startDt;

    private LocalDate endDt;

}
