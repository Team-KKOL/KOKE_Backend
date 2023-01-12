package com.koke.koke_backend.fav.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QFavRepositoryImpl implements QFavRepository {

    private final JPAQueryFactory queryFactory;

}
