package com.koke.koke_backend.order.entity;

import com.koke.koke_backend.common.entity.BaseIdEntity;
import com.koke.koke_backend.order.enums.Cycle;
import com.koke.koke_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Subscribe extends BaseIdEntity {

    @Column(unique = true, length = 50)
    @Comment("고유번호")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Comment("구독 주기")
    private Cycle cycle;

    @Column(nullable = false)
    @Comment("구독 신청일")
    private LocalDate subscribeDt;

    @Column(nullable = false)
    @Comment("배송 예정일")
    private LocalDate deliveryDt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @Comment("사용자 ID")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderProductId", referencedColumnName = "id")
    @Comment("상품 주문 ID")
    private OrderProduct orderProduct;

}
