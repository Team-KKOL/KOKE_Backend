package com.koke.koke_backend.user.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

}
