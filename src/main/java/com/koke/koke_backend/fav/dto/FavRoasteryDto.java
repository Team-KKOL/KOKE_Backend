package com.koke.koke_backend.fav.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavRoasteryDto {

    private String roasteryNm;
    private List<String> photoImgUrl;

}
