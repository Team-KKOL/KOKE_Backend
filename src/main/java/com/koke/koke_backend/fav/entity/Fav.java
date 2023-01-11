package com.koke.koke_backend.fav.entity;

import com.koke.koke_backend.common.entity.BaseEntity;
import com.koke.koke_backend.fav.enums.FavType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

}
