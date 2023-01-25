package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.common.jpa.PredicateBuilder;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.koke.koke_backend.product.entity.QProduct.product;
import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;

@RequiredArgsConstructor
public class QProductRepositoryImpl implements QProductRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductListResponseDto> list(ProductListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo() - 1, dto.getPageSize());

        PredicateBuilder.builder()
                .containsString(product.name, dto.getProductNm())
                .containsString(roastery.roasteryNm, dto.getRoasteryNm())
                .eqStringList(product.flavor, dto.getFlavor())
                .build();

        queryFactory.select(Projections.fields(ProductListResponseDto.class,
                        product.id,
                        product.photoImgUrl,
                        product.flavor,
                        product.name.as("productNm"),
                        product.price,
                        roastery.roasteryNm
                ))
                .from(product)
                .join(product.roastery, roastery)
                .fetch();

        return null;
    }
}
