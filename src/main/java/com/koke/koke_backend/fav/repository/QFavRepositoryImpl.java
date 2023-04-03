package com.koke.koke_backend.fav.repository;

import com.koke.koke_backend.fav.dto.FavProductDto;
import com.koke.koke_backend.fav.dto.FavRoasteryDto;
import com.koke.koke_backend.fav.entity.Fav;
import com.koke.koke_backend.fav.enums.FavType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.koke.koke_backend.fav.entity.QFav.fav;
import static com.koke.koke_backend.product.entity.QProduct.product;
import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;
import static com.koke.koke_backend.user.entity.QUser.user;

@RequiredArgsConstructor
public class QFavRepositoryImpl implements QFavRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Fav> getFavRoastery(String userId, String roasteryUuid) {
        Fav favEntity = queryFactory.selectFrom(fav)
                .join(fav.user, user)
                .join(fav.roastery)
                .where(user.id.eq(userId), roastery.uuid.eq(roasteryUuid), fav.favType.eq(FavType.ROASTERY))
                .fetchOne();
        return Optional.ofNullable(favEntity);
    }

    @Override
    public Optional<Fav> getFavProduct(String userId, String productUuid) {
        Fav favEntity = queryFactory.selectFrom(fav)
                .join(fav.user, user)
                .join(fav.product, product)
                .where(user.id.eq(userId), product.uuid.eq(productUuid), fav.favType.eq(FavType.PRODUCT))
                .fetchOne();
        return Optional.ofNullable(favEntity);
    }


    @Override
    public List<FavRoasteryDto> favRoasteryList(String userId) {
        return queryFactory
                .select(Projections.fields(FavRoasteryDto.class,
                        roastery.name,
                        roastery.photoImgUrl
                ))
                .from(fav)
                .join(fav.user, user)
                .join(fav.roastery, roastery)
                .where(user.id.eq(userId), fav.favType.eq(FavType.ROASTERY))
                .fetch();
    }

    @Override
    public List<FavProductDto> favProductList(String userId) {
        return queryFactory
                .select(Projections.fields(FavProductDto.class,
                        product.name.as("productNm"),
                        roastery.logoImgUrl.as("logoImgUrl"),
                        product.photoImgUrl.as("photoImgUrl"),
                        product.flavor,
                        product.price,
                        roastery.name.as("roasteryNm")
                ))
                .from(fav)
                .join(fav.user, user)
                .join(fav.product, product)
                .join(product.roastery, roastery)
                .where(user.id.eq(userId), fav.favType.eq(FavType.PRODUCT))
                .fetch();
    }
}
