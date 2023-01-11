package com.koke.koke_backend.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.common.enums.Yn;
import com.koke.koke_backend.common.security.jwt.Role;
import com.koke.koke_backend.common.security.oauth.info.ProviderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "userId"),
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(length = 30)
    @Comment("사용자 PK")
    private Long userSeq;

    @Column(length = 30)
    @Comment("사용자 Id")
    private String userId;

    @Column(length = 100)
    @Comment("사용자명")
    private String username;

    @JsonIgnore
    @Column(length = 128)
    @Comment("비밀번호")
    private String password;

    @Column(length = 512)
    @Comment("이메일")
    private String email;

    @Enumerated(STRING)
    @Column(nullable = false, length = 1)
    @Comment("이메일 인증 여부")
    private Yn emailVerifiedYn;

    @Column(length = 512)
    @Comment("프로필 이미지 URL")
    private String profileImageUrl;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private ProviderType providerType;

    @Enumerated(STRING)
    @Column(length = 20)
    private Role role;

}
