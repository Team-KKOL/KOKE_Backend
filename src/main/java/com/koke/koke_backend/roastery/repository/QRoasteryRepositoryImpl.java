package com.koke.koke_backend.roastery.repository;

import com.koke.koke_backend.roastery.dto.RoasteryDetailResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryTop4ResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;
import static com.querydsl.core.types.Projections.fields;

@RequiredArgsConstructor
public class QRoasteryRepositoryImpl implements QRoasteryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RoasteryListResponseDto> list(SortType sortType) {
        return queryFactory.select(fields(RoasteryListResponseDto.class,
                        roastery.id,
                        roastery.roasteryNm,
                        roastery.logoImgUrl
                ))
                .from(roastery)
                .orderBy(getOrderSpecifier(sortType))
                .fetch();
    }

    @Override
    public Optional<RoasteryDetailResponseDto> detail(String id) {
        RoasteryDetailResponseDto fetchOne = queryFactory.select(fields(RoasteryDetailResponseDto.class,
                        roastery.id,
                        roastery.roasteryNm,
                        roastery.description,
                        roastery.awards,
                        roastery.location,
                        roastery.webUrl,
                        roastery.snsUrl,
                        roastery.logoImgUrl,
                        roastery.photoImgUrl,
                        roastery.insDtm
                ))
                .from(roastery)
                .where(roastery.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(fetchOne);
    }

    @Override
    public List<RoasteryTop4ResponseDto> top4() {
        List<RoasteryTop4ResponseDto> fetch = queryFactory.select(fields(RoasteryTop4ResponseDto.class,
                        roastery.id,
                        roastery.roasteryNm,
                        roastery.logoImgUrl,
                        roastery.photoImgUrl,
                        roastery.location
                ))
                .from(roastery)
                .fetch();

        Collections.shuffle(fetch);

        return fetch.subList(0, 4);
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
