package com.koke.koke_backend.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QOrderProductRepositoryImpl implements QOrderProductRepository {

    private final JPAQueryFactory queryFactory;

}
