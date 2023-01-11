package com.koke.koke_backend.roastery.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QRoasteryRepositoryImpl implements QRoasteryRepository {

    private final JPAQueryFactory queryFactory;

}
