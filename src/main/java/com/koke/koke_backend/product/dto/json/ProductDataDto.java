package com.koke.koke_backend.product.dto.json;

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
public class ProductDataDto {

    @JsonProperty("CoffeeName")
    private String name;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("Weight")
    private String weight;

    @JsonProperty("Summary")
    private String summary;

    @JsonProperty("ImgURL")
    private String photoImgUrl;

    @JsonProperty("Roastery")
    private String roastery;

    @JsonProperty("Flavor")
    private List<String> flavor;

    @JsonProperty("ExtractionType")
    private List<String> extractionType;

    @JsonProperty("CookingType")
    private List<String> cookingType;

    @JsonProperty("style")
    private List<String> style;

}
