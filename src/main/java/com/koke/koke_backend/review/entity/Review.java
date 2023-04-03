package com.koke.koke_backend.review.entity;

import com.koke.koke_backend.common.entity.BaseIdEntity;
import com.koke.koke_backend.order.entity.OrderProduct;
import com.koke.koke_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Review extends BaseIdEntity {

    @Column(nullable = false, length = 3)
    private String score;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "orderProductId", referencedColumnName = "id")
    private OrderProduct orderProduct;

}
