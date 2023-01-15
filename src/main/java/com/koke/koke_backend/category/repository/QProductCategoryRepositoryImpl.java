package com.koke.koke_backend.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QProductCategoryRepositoryImpl implements QProductCategoryRepository {

    private final JPAQueryFactory queryFactory;

}
