package com.koke.koke_backend.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.order.enums.GrindType;
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
    private OrderInfo orderInfo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @Comment("상품 ID")
    private Product product;

    @JsonBackReference
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Comment("주문 상태")
    private OrderStatus orderStatus;

    @Column(nullable = false, length = 10)
    @Comment("원두 용량")
    private String volume;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Comment("원두 분쇄 여부")
    private GrindType grind;

    @OneToOne(mappedBy = "orderProduct")
    @JoinColumn(name = "reviewId", referencedColumnName = "id")
    private Review review;

}
