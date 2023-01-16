package com.koke.koke_backend.subscribe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QSubscribeRepositoryImpl implements QSubscribeRepository {

    private final JPAQueryFactory queryFactory;

}
