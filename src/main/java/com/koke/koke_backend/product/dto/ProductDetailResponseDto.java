package com.koke.koke_backend.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDto {

    private String id;

    private String uuid;

    private String productNm;

    private String description;

    private List<String> flavor;

    private List<String> weight;

    private List<String> extractionType;

    private List<String> cookingType;

    private List<String> style;

    private String price;

    private String photoImgUrl;

    private String roasteryId;

    private String roasteryNm;

}
