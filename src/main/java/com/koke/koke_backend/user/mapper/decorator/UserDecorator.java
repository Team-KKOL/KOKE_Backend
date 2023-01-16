package com.koke.koke_backend.user.mapper.decorator;

import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

@Primary
public abstract class UserDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signUpRequestDtoToUser(SignUpRequestDto signUpRequestDto) {
        User user = delegate.signUpRequestDtoToUser(signUpRequestDto);

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        return user.toBuilder()
                .password(encodedPassword)
                .build();
    }
}
