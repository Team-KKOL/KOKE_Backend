package com.koke.koke_backend.order.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.order.enums.OrderStatus;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.review.entity.Review;
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
public class OrderProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Comment("상품 주문 ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    @Comment("주문 ID")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @Comment("상품 ID")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "orderProduct")
    @JoinColumn(name = "reviewId", referencedColumnName = "id")
    private Review review;

}
