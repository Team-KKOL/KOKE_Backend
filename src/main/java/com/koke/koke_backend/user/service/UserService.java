package com.koke.koke_backend.user.service;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.AccessToken;
import com.koke.koke_backend.common.security.JwtTokenProvider;
import com.koke.koke_backend.common.security.RefreshToken;
import com.koke.koke_backend.common.security.Role;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.mapper.UserMapper;
import com.koke.koke_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Object>> checkUserId(String userId) {
        return userRepository.findById(userId)
                .map(user -> ApiResponse.badRequest("이미 사용중인 사용자 ID입니다."))
                .orElse(ApiResponse.success("사용 가능한 사용자 ID입니다."));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Object>> checkEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> ApiResponse.badRequest("이미 사용중인 이메일입니다."))
                .orElse(ApiResponse.success("사용 가능한 이메일입니다."));
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> signUp(SignUpRequestDto signUpRequestDto) {
        User user = userMapper.signUpRequestDtoToUser(signUpRequestDto);
        userRepository.save(user);

        return ApiResponse.success("회원가입에 성공했습니다.");
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("가입되지 않은 아이디입니다."));

        boolean pwIsMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (pwIsMatch) {
            AccessToken accessToken = jwtTokenProvider.createAccessToken(loginRequestDto.getUserId(), Role.USER);
            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(loginRequestDto.getUserId(), Role.USER);

            Map<String, Object> result = new HashMap<>();
            result.put("accessToken", accessToken);
            result.put("refreshToken", refreshToken);

            return ApiResponse.success(result);
        } else {
            return ApiResponse.badRequest("비밀번호가 일치하지 않습니다.");
        }
    }

    public ResponseEntity<ApiResponse<Object>> logout() {
        return null;
    }
}
