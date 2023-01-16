package com.koke.koke_backend.fav.entity;

import com.koke.koke_backend.common.entity.BaseEntity;
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
import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Fav extends BaseEntity {

    // 상품, 로스터리 찜할 수 있어야 함

    @Id
    @GeneratedValue(strategy =  IDENTITY)
    @Column(length = 30)
    @Comment("좋아요 ID")
    private Long favId;

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
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @Comment("좋아요한 사용자 ID")
    private User user;

}
