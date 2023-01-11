package com.koke.koke_backend.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QProductRepositoryImpl implements QProductRepository {

    private final JPAQueryFactory queryFactory;



}
