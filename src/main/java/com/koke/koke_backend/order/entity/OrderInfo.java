package com.koke.koke_backend.order.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koke.koke_backend.common.entity.BaseIdEntity;
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
public class OrderInfo extends BaseIdEntity {

    @Column(unique = true, length = 50)
    @Comment("고유번호")
    private String uuid;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "id")
    @Comment("사용자 ID")
    private User user;

    @JsonManagedReference
    @Builder.Default
    @OneToMany(mappedBy = "orderInfo",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

}
