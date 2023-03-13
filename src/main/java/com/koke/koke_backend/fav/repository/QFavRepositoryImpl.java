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
    public Optional<Fav> getFavRoastery(String userId, String roasteryId) {
        Fav favEntity = queryFactory.selectFrom(fav)
                .join(fav.user, user)
                .join(fav.roastery)
                .where(user.userId.eq(userId), roastery.id.eq(roasteryId), fav.favType.eq(FavType.ROASTERY))
                .fetchOne();
        return Optional.ofNullable(favEntity);
    }

    @Override
    public Optional<Fav> getFavProduct(String userId, String productId) {
        Fav favEntity = queryFactory.selectFrom(fav)
                .join(fav.user, user)
                .join(fav.product, product)
                .where(user.userId.eq(userId), product.id.eq(productId), fav.favType.eq(FavType.PRODUCT))
                .fetchOne();
        return Optional.ofNullable(favEntity);
    }


    @Override
    public List<FavRoasteryDto> favRoasteryList(String userId) {
        return queryFactory
                .select(Projections.fields(FavRoasteryDto.class,
                        roastery.roasteryNm,
                        roastery.photoImgUrl
                ))
                .from(fav)
                .join(fav.user, user)
                .join(fav.roastery, roastery)
                .where(user.userId.eq(userId), fav.favType.eq(FavType.ROASTERY))
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
                        roastery.roasteryNm
                ))
                .from(fav)
                .join(fav.user, user)
                .join(fav.product, product)
                .join(product.roastery, roastery)
                .where(user.userId.eq(userId), fav.favType.eq(FavType.PRODUCT))
                .fetch();
    }
}
