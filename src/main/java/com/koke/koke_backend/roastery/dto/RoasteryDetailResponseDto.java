package com.koke.koke_backend.roastery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String uuid;
    private String roasteryNm;
    private List<String> description;
    private List<String> awards;
    private String location;
    private String webUrl;
    private String snsUrl;
    private String logoImgUrl;
    private List<String> photoImgUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insDtm;

}
