package com.koke.koke_backend.roastery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoasteryCreateRequestDto {

    @NotBlank(message = "로스터리 카페명을 입력하지 않으셨습니다.")
    private String name;

    @NotEmpty(message = "로스터리 카페 소개를 입력하지 않으셨습니다")
    @NotBlank(message = "로스터리 카페 소개를 입력하지 않으셨습니다")
    private List<String> description;

    private List<String> awards;

    @NotBlank(message = "로스터리 카페 주소를 입력하지 않으셨습니다.")
    private String location;

    private String webUrl;

    private String snsUrl;

    @NotBlank(message = "로스터리 카페 로고 이미지 URL을 입력하지 않으셨습니다.")
    private String logoImgUrl;

    @NotNull(message = "로스터리 카페 이미지 URL을 입력하지 않으셨습니다.")
    @NotEmpty(message = "로스터리 카페 이미지 URL을 입력하지 않으셨습니다.")
    private List<String> photoImgUrl;

}
