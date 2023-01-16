package com.koke.koke_backend.user.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.common.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.EnumType.STRING;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseTimeEntity {

    @Id
    @Column(length = 30)
//    @Pattern(regexp = "^[a-z0-9]{4,30}$", message = "아이디는 영문, 숫자 4~30자로 입력해주세요.")
    @Comment("사용자 Id")
    private String userId;

    @Column(length = 128)
    @Comment("비밀번호")
    private String password;

    @Column(length = 40)
    @Comment("이메일")
    private String email;

    @Column(length = 10)
    @Comment("사용자명")
    private String username;

    @Column(length = 20)
    @Comment("전화번호")
    private String phoneNo;

    @Enumerated(STRING)
    @Column(length = 20)
    private Role role;

}
