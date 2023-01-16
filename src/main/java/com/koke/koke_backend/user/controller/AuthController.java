package com.koke.koke_backend.user.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.RefreshRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 관리", description = "인증 관리 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "아이디 중복확인", description = "아이디 중복확인 API")
    @GetMapping("/check/id")
    public ResponseEntity<ApiResponse<Object>> checkUserId(@RequestParam(name = "userId") String userId) {
        return authService.checkUserId(userId);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "이메일 중복확인", description = "이메일 중복확인 API")
    @GetMapping("/check/email")
    public ResponseEntity<ApiResponse<Object>> checkEmail(@RequestParam(name = "email") String email) {
        return authService.checkEmail(email);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "회원가입", description = "회원가입 API")
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<Object>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "로그아웃", description = "로그아웃 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping("/logoutUser")
    public ResponseEntity<ApiResponse<Object>> logout() {
        return authService.logout();
    }

    @Tag(name = "인증 관리", description = "인증 관리 API")
    @Operation(summary = "Access Token 재발급", description = "Access Token 재발급 API")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<Object>> refresh(@RequestBody RefreshRequestDto dto) {
        return authService.refresh(dto);
    }

}
