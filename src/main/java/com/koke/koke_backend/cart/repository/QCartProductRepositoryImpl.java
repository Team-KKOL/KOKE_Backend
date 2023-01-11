package com.koke.koke_backend.cart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QCartProductRepositoryImpl implements QCartProductRepository {

    private final JPAQueryFactory queryFactory;

}
