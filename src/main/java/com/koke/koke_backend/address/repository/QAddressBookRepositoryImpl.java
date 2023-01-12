package com.koke.koke_backend.address.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QAddressBookRepositoryImpl implements QAddressBookRepository {

    private final JPAQueryFactory queryFactory;

}
