package com.koke.koke_backend.roastery.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoasteryTop4ResponseDto {

    private String id;

    private String roasteryNm;

    private String logoImgUrl;

    @JsonIgnore
    private List<String> photoImgUrl;

    private String location;

    @JsonProperty("photoImgUrl")
    public String getFirstPhotoImgUrl() {
        return photoImgUrl.get(0);
    }

}
