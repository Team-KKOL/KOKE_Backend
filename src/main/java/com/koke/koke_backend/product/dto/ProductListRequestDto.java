package com.koke.koke_backend.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListRequestDto {

    @Builder.Default
    @Min(value = 1, message = "페이지 번호는 1 이상으로 입력해주세요.")
    private Integer pageNo = 1;

    @Builder.Default
    @Min(value = 10, message = "페이지 번호는 10 이상으로 입력해주세요.")
    private Integer pageSize = 10;

    @Size(max = 30, message = "상품명은 30자 이하로 입력해주세요.")
    private String productNm;

    @Size(max = 30, message = "로스터리 카페명은 30자 이하로 입력해주세요.")
    private String roasteryNm;

    private List<String> flavor;
    private List<String> roastingPoint;
    private List<String> origin;
    private List<String> processingMethod;
    private List<String> characteristics;


}
