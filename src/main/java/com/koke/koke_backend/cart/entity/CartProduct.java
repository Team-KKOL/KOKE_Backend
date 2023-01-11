package com.koke.koke_backend.cart.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.product.entity.Product;
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
public class CartProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(length = 30)
    @Comment("장바구니 상품 ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    @Comment("장바구니 ID")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @Comment("상품 ID")
    private Product product;

}
