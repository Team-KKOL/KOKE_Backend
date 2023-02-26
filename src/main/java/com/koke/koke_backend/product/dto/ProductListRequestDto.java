package com.koke.koke_backend.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "페이지 번호 (동적)", example = "1")
    @Builder.Default
    @Min(value = 1, message = "페이지 번호는 1 이상으로 입력해주세요.")
    private Integer pageNo = 1;

    @Schema(description = "페이지 크기 (동적)", example = "10")
    @Builder.Default
    @Min(value = 10, message = "페이지 번호는 10 이상으로 입력해주세요.")
    private Integer pageSize = 10;

    @Schema(description = "로스터리 카페명 (동적)", example = "필리그리커피")
    @Size(max = 30, message = "상품명은 30자 이하로 입력해주세요.")
    private String productNm;

    @Schema(description = "로스터리 카페명", example = "")
    @Size(max = 30, message = "로스터리 카페명은 30자 이하로 입력해주세요.")
    private String roasteryNm;

    @Schema(description = "맛 (동적)", example = "[\"고소\"]")
    private List<String> flavor;

    @Schema(description = "디카페인 여부", example = "false")
    private Boolean decaffeineYn;

    private List<String> roastingPoint;
    private List<String> origin;
    private List<String> processingMethod;
    private List<String> characteristics;


}
