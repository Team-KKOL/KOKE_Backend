package com.koke.koke_backend.order.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Order extends BaseTimeEntity {

    @Id
    @Column(length = 50)
    @Comment("주문 ID")
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "userId")
    @Comment("사용자 ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

}
