package com.koke.koke_backend.roastery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoasteryDataDto {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("IconURL")
    private String iconUrl;

    @JsonProperty("ImgURLS")
    private List<ImgUrlDto> imgUrls;

    @JsonProperty("Summary")
    private String summary;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("Instagram")
    private String instagram;

    @JsonProperty("WebSite")
    private String webSite;

}
