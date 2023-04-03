package com.koke.koke_backend.fav.entity;

import com.koke.koke_backend.common.entity.BaseIdEntity;
import com.koke.koke_backend.fav.enums.FavType;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Fav extends BaseIdEntity {

    // 상품, 로스터리 찜할 수 있어야 함

    @Column(unique = true, length = 50)
    @Comment("고유번호")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @Comment("좋아요 타입")
    private FavType favType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @Comment("좋아요 대상 상품 ID")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "roasteryId", referencedColumnName = "id")
    @Comment("좋아요 대상 로스터리 ID")
    private Roastery roastery;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @Comment("좋아요한 사용자 ID")
    private User user;

}
