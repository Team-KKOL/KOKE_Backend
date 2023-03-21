package com.koke.koke_backend.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.product.dto.ProductDetailResponseDto;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import com.koke.koke_backend.product.mapper.ProductMapper;
import com.koke.koke_backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.koke.koke_backend.common.singleton.Constant.getNSEE;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Page<ProductListResponseDto>>> list(ProductListRequestDto dto) {
        Page<ProductListResponseDto> list = productRepository.list(dto);
        return ApiResponse.success(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<ProductDetailResponseDto>> detail(String id) {
        return productRepository.detail(id)
                .map(ApiResponse::success)
                .orElseThrow(getNSEE.apply("커피 정보"));
    }

}
