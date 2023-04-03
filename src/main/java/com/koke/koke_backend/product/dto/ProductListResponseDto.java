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
public class ProductListResponseDto {

    private String uuid;
    private String photoImgUrl;
    private List<String> flavor;
    private String productNm;
    private String price;
    private String roasteryNm;

}
