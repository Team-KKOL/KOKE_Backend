package com.koke.koke_backend.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class QProductRepositoryImpl implements QProductRepository {

    private final JPAQueryFactory queryFactory;

    public QProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
