package com.koke.koke_backend.product.service;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.product.dto.ProductDetailResponseDto;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
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

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<Page<ProductListResponseDto>>> list(ProductListRequestDto dto) {
        Page<ProductListResponseDto> list = productRepository.list(dto);
        return ResponseMapper.success(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<ProductDetailResponseDto>> detail(String uuid) {
        return productRepository.detail(uuid)
                .map(ResponseMapper::success)
                .orElseThrow(getNSEE.apply("커피 정보"));
    }

}
