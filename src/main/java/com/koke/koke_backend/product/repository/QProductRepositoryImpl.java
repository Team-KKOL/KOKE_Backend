package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.common.jpa.PredicateBuilder;
import com.koke.koke_backend.product.dto.ProductListRequestDto;
import com.koke.koke_backend.product.dto.ProductListResponseDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.koke.koke_backend.product.entity.QProduct.product;
import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QProductRepositoryImpl implements QProductRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductListResponseDto> list(ProductListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo() - 1, dto.getPageSize());

        Predicate predicate = PredicateBuilder.builder()
                .containsString(product.name, dto.getProductNm())
                .containsString(roastery.roasteryNm, dto.getRoasteryNm())
                .eqStringList(product.flavor, dto.getFlavor())
                .build();

        List<ProductListResponseDto> fetch = queryFactory
                .select(fields(ProductListResponseDto.class,
                        product.id,
                        product.photoImgUrl,
                        product.flavor,
                        product.name.as("productNm"),
                        product.price,
                        roastery.roasteryNm
                ))
                .from(product)
                .join(product.roastery, roastery)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.name.asc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(product.id.count())
                .from(product)
                .join(product.roastery, roastery)
                .where(predicate);

        return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne);
    }
}
