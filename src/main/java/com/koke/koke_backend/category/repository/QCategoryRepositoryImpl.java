package com.koke.koke_backend.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QCategoryRepositoryImpl implements QCategoryRepository {

    private final JPAQueryFactory queryFactory;

}
