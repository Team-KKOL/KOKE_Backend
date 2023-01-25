package com.koke.koke_backend.product.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import com.koke.koke_backend.product.dto.json.ProductDataDto;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.product.mapper.ProductMapper;
import com.koke.koke_backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Transactional
    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        File file = new ClassPathResource("coffee2.json").getFile();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);

        List<ProductDataDto> productDataDtos = objectMapper.readValue(file, new TypeReference<>() {});
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productDataDtos));

        List<Product> list = productMapper.toEntityList(productDataDtos);
        productRepository.saveAll(list);

        return ApiResponse.success();
    }
}
