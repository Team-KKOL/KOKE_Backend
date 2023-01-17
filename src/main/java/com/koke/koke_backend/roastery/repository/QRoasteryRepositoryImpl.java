package com.koke.koke_backend.roastery.repository;

import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QRoasteryRepositoryImpl implements QRoasteryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RoasteryListResponseDto> list(SortType sortType) {
        return queryFactory.select(fields(RoasteryListResponseDto.class,
                        roastery.roasteryNm,
                        roastery.logoImgUrl
                ))
                .from(roastery)
                .orderBy(getOrderSpecifier(sortType))
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(SortType sortType) throws IllegalStateException {
        switch (sortType) {
            case NAME -> {
                return roastery.roasteryNm.asc();
            }
            case RECENT -> {
                return roastery.insDtm.desc();
            }
            default -> throw new IllegalStateException("Unexpected value: " + sortType);
        }
    }

}
