package com.koke.koke_backend.roastery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoasteryCreateRequestDto {

    @NotBlank(message = "로스터리 카페명을 입력하지 않으셨습니다.")
    private String roasteryNm;

    private String description;

    private String location;

    private String snsUrl;

    private String logoImgUrl;

    private String photoImgUrl;

}
