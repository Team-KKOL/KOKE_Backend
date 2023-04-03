package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.product.dto.ProductDetailResponseDto;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QProductRepository {
    Optional<ProductDetailResponseDto> detail(String uuid);
    Page<ProductListResponseDto> list(ProductListRequestDto dto);
}
