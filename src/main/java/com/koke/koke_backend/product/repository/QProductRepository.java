package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import org.springframework.data.domain.Page;

public interface QProductRepository {
    Page<ProductListResponseDto> list(ProductListRequestDto dto);
}
