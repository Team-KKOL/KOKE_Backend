package com.koke.koke_backend.fav.entity;

import com.koke.koke_backend.common.entity.BaseEntity;
import com.koke.koke_backend.fav.enums.FavType;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.roastery.entity.Roastery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    private Long favId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private FavType favType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "roasteryId", referencedColumnName = "roasteryId")
    private Roastery roastery;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "userEmail", referencedColumnName = "email")
//    private User user;

}
