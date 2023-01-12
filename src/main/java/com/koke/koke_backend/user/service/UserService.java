package com.koke.koke_backend.user.service;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<ApiResponse<?>> signUp(SignUpRequestDto signUpRequestDto) {
        return null;
    }
}
