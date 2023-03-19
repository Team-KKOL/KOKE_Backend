package com.koke.koke_backend.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.koke.koke_backend.common.json.serializer.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime subscribeDtm;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonProperty("deliveryDtm")
    public LocalDateTime getDeliveryDtm() {
        switch(subscribeDtm.getDayOfWeek()) {
            case SATURDAY -> {
                return subscribeDtm.plusDays(2);
            }
            case SUNDAY -> {
                return subscribeDtm.plusDays(1);
            }
            default -> {
                return subscribeDtm;
            }
        }
    }

}
