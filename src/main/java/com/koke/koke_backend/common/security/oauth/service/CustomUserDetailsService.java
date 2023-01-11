package com.koke.koke_backend.common.security.oauth.service;

import com.koke.koke_backend.common.security.oauth.vo.OAuth2UserPrincipal;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);

        if (user == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }

        return OAuth2UserPrincipal.create(user);
    }
}
