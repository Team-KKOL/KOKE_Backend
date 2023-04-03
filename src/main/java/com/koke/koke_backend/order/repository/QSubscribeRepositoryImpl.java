package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.dto.SubscribeListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koke.koke_backend.order.entity.QOrderProduct.orderProduct;
import static com.koke.koke_backend.order.entity.QSubscribe.subscribe;
import static com.koke.koke_backend.product.entity.QProduct.product;
import static com.koke.koke_backend.roastery.entity.QRoastery.roastery;
import static com.koke.koke_backend.user.entity.QUser.user;

@RequiredArgsConstructor
public class QSubscribeRepositoryImpl implements QSubscribeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SubscribeListResponseDto> list(String userId) {
        return queryFactory.select(
                        Projections.fields(SubscribeListResponseDto.class,
                                subscribe.id,
                                product.id.as("productId"),
                                product.name.as("productNm"),
                                product.photoImgUrl.as("photoImgUrl"),
                                product.flavor,
                                roastery.name.as("roasteryNm"),
                                subscribe.subscribeDt,
                                subscribe.deliveryDt
                                )
                )
                .from(subscribe)
                .join(subscribe.user, user)
                .join(subscribe.orderProduct, orderProduct)
                .join(orderProduct.product, product)
                .join(product.roastery, roastery)
                .where(user.id.eq(userId))
                .fetch();
    }

}
