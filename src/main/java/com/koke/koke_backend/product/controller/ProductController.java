package com.koke.koke_backend.product.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.product.dto.ProductDetailResponseDto;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import com.koke.koke_backend.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "커피 관리", description = "커피 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Tag(name = "커피 관리", description = "커피 관리 API")
    @Operation(summary = "커피 리스트 조회", description = "커피 리스트 조회 API")
    @PostMapping
    public ResponseEntity<ApiResponse<Page<ProductListResponseDto>>> list(@Valid @RequestBody ProductListRequestDto dto) {
        return productService.list(dto);
    }

    @Tag(name = "커피 관리", description = "커피 관리 API")
    @Operation(summary = "커피 상세 조회", description = "커피 상세 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDetailResponseDto>> detail(@PathVariable String id) {
        return productService.detail(id);
    }

    @Tag(name = "커피 관리", description = "커피 관리 API")
    @Operation(summary = "커피 데이터 파싱 생성 - 테스트", description = "커피 데이터 파싱 생성 API - 테스트")
    @GetMapping("/parse")
    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        return productService.parse();
    }

}
