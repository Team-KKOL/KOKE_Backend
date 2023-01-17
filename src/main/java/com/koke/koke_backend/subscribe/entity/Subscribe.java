package com.koke.koke_backend.subscribe.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.subscribe.enums.Cycle;
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
public class Subscribe extends BaseTimeEntity {

    @Id
    @Column(length = 30)
    @Comment("구독 ID")
    private String subscribeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Comment("구독 주기")
    private Cycle cycle;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @Comment("사용자 ID")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderProductId", referencedColumnName = "id")
    @Comment("상품 주문 ID")
    private OrderProduct orderProduct;



}
