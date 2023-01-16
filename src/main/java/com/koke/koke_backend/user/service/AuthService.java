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
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, AccessToken> redisTemplateAccess;
    private final RedisTemplate<String, RefreshToken> redisTemplateRefresh;

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
        HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
        ValueOperations<String, AccessToken> opsForValue = redisTemplateAccess.opsForValue();
        ValueOperations<String, RefreshToken> opsForValue2 = redisTemplateRefresh.opsForValue();

        String userId = null;
        String accessToken = jwtTokenProvider.resolveToken(request);

        try {
            userId = jwtTokenProvider.getIdFromToken(accessToken); // access_token에서 user_id를 가져옴(유효성 검사)
        } catch (IllegalArgumentException ignored) {
        } catch (ExpiredJwtException e) { // 만료
            userId = e.getClaims().getSubject(); // 만료된 access token으로부터 user_id를 가져옴
            log.info("user_id from expired access token : " + userId);
        }

        if (opsForValue.get("access_" + userId) != null) {
            redisTemplateAccess.delete("access_" + userId);
//            opsForValue.set(accessToken, true);
//            redisTemplateAccess.expire(accessToken, 1, HOURS);
        }

        try {
            Object refreshTokenObject = opsForValue2.get("refresh_" + userId);
            if (refreshTokenObject != null) { // refresh token이 db에 저장되어있을때
                redisTemplateAccess.delete("refresh_" + userId); // refresh token의 데이터를 삭제
            }
        } catch (IllegalArgumentException e) {
            log.warn("user does not exist");
        }

        log.info(" logout ing : " + accessToken);

        return ApiResponse.success("로그아웃 되었습니다.");
    }
}
