package com.koke.koke_backend.file.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QFileInfoRepositoryImpl implements QFileInfoRepository {

    private final JPAQueryFactory queryFactory;

}
