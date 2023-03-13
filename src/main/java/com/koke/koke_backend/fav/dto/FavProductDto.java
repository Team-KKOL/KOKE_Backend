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
public class FavProductDto {

    private String productNm;
    private String logoImgUrl;
    private String photoImgUrl;
    private List<String> flavor;
    private String price;
    private String roasteryNm;


//    	"photoImgUrl": "string",
//                "logoImgUrl" : "string",
//                "flavor": "string",
//                "productNm": "string",
//                "price" : "string",
//                "roasteryNm": "string"

}
