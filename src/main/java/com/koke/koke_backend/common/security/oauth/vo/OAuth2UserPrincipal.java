package com.koke.koke_backend.common.security.oauth.vo;

import com.koke.koke_backend.common.security.jwt.Role;
import com.koke.koke_backend.common.security.oauth.info.ProviderType;
import com.koke.koke_backend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

import static java.util.Collections.singletonList;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class OAuth2UserPrincipal implements OAuth2User, UserDetails, OidcUser {

    private final String userId;
    private final String password;
    private final ProviderType providerType;
    private final Role role;
    private final Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return userId;
    }

    @Override
    public String getUsername() {
        return userId;
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

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    public static OAuth2UserPrincipal create(User user) {
        return new OAuth2UserPrincipal(
                user.getUserId(),
                user.getPassword(),
                user.getProviderType(),
                Role.USER,
                singletonList(Role.USER)
        );
    }

    public static OAuth2UserPrincipal create(User user, Map<String, Object> attributes) {
        OAuth2UserPrincipal userPrincipal = create(user);
        userPrincipal.setAttributes(attributes);

        return userPrincipal;
    }

}
