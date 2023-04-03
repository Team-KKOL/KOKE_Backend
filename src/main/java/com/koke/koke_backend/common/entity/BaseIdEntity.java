package com.koke.koke_backend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@ToString
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseIdEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 40)
    private Long id;

    @Builder.Default
    @Column(nullable = false)
    private Boolean enabled = false;

    public void disable() {
        this.enabled = true;
    }

}
