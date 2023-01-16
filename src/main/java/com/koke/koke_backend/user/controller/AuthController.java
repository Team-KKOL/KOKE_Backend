package com.koke.koke_backend.user.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/check/id")
    public ResponseEntity<ApiResponse<Object>> checkUserId(@RequestParam(name = "userId") String userId) {
        return userService.checkUserId(userId);
    }

    @GetMapping("/check/email")
    public ResponseEntity<ApiResponse<Object>> checkEmail(@RequestParam(name = "email") String email) {
        return userService.checkEmail(email);
    }

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<Object>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<ApiResponse<Object>> logout() {
        return userService.logout();
    }

}
