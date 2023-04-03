package com.koke.koke_backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeListResponseDto {
    private String subscribeId;
    private String productId;
    private String productNm;
    private String photoImgUrl;
    private List<String> flavor;
    private String roasteryNm;
    private LocalDate subscribeDt;
    private LocalDate deliveryDt;

}
