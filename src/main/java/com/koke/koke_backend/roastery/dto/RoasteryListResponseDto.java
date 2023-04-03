package com.koke.koke_backend.roastery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoasteryListResponseDto {
    private String uuid;
    private String name;
    private String logoImgUrl;

}
