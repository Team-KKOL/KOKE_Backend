package com.koke.koke_backend.common.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUser extends User {

    private final com.koke.koke_backend.user.entity.User user;

    public CustomUser(com.koke.koke_backend.user.entity.User user) {
        super(user.getId(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

}
