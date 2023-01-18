package com.koke.koke_backend.user.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.common.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static jakarta.persistence.EnumType.STRING;
import static java.util.Collections.singletonList;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(length = 30)
    @Comment("사용자 ID")
    private String userId;

    @Column(length = 100)
    @Comment("비밀번호")
    private String password;

    @Column(length = 40)
    @Comment("이메일")
    private String email;

    @Column(length = 10)
    @Comment("사용자명")
    private String name;

    @Column(length = 20)
    @Comment("전화번호")
    private String phoneNo;

    @Enumerated(STRING)
    @Column(length = 20)
    private Role role;

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
