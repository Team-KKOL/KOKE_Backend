package com.koke.koke_backend.common.security.oauth.service;

import com.koke.koke_backend.common.security.oauth.exception.OAuthProviderMissMatchException;
import com.koke.koke_backend.common.security.oauth.info.OAuth2UserInfo;
import com.koke.koke_backend.common.security.oauth.info.OAuth2UserInfoFactory;
import com.koke.koke_backend.common.security.oauth.info.ProviderType;
import com.koke.koke_backend.common.security.oauth.vo.OAuth2UserPrincipal;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.mapper.UserMapper;
import com.koke.koke_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return this.process(userRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());
        User savedUser = userRepository.findByUserId(oAuth2UserInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
        } else {
            savedUser = createUser(oAuth2UserInfo, providerType);
        }

        return OAuth2UserPrincipal.create(savedUser, oAuth2UserInfo.getAttributes());
    }

    private User createUser(OAuth2UserInfo oAuth2UserInfo, ProviderType providerType) {
        User user = userMapper.toEntity(oAuth2UserInfo, providerType);
        return userRepository.saveAndFlush(user);
    }

}
