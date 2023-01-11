package com.koke.koke_backend.file.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QFileMstRepositoryImpl implements QFileMstRepository {

    private final JPAQueryFactory queryFactory;

}
