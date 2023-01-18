package com.koke.koke_backend.roastery.dto;

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
public class RoasteryDetailResponseDto {

    private String id;
    private String roasteryNm;
    private List<String> description;
    private List<String> awards;
    private String location;
    private String webUrl;
    private String snsUrl;
    private String logoImgUrl;
    private List<String> photoImgUrl;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime insDtm;

}
