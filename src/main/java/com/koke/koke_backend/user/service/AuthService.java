package com.koke.koke_backend.user.service;

import com.koke.koke_backend.address.entity.AddressBook;
import com.koke.koke_backend.address.mapper.AddressBookMapper;
import com.koke.koke_backend.address.repository.AddressBookRepository;
import com.koke.koke_backend.cart.entity.Cart;
import com.koke.koke_backend.cart.mapper.CartMapper;
import com.koke.koke_backend.cart.repository.CartRepository;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.JwtTokenProvider;
import com.koke.koke_backend.common.security.Role;
import com.koke.koke_backend.common.security.token.AccessToken;
import com.koke.koke_backend.common.security.token.RefreshToken;
import com.koke.koke_backend.common.yml.JwtProperty;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.RefreshRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import com.koke.koke_backend.user.entity.User;
import com.koke.koke_backend.user.mapper.UserMapper;
import com.koke.koke_backend.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AddressBookRepository addressBookRepository;
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final AddressBookMapper addressBookMapper;
    private final CartMapper cartMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, AccessToken> redisTemplateAccess;
    private final RedisTemplate<String, RefreshToken> redisTemplateRefresh;
    private final RedisTemplate<String, Object> redisTemplateObject;
    private final JwtProperty jwtProperty;

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
        userRepository.saveAndFlush(user);

        user = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new NoSuchElementException("사용자가 존재하지 않습니다."));

        AddressBook addressBook = addressBookMapper.createAddressBook(user);
        addressBookRepository.save(addressBook);

        Cart cart = cartMapper.createCart(user);
        cartRepository.save(cart);

        return ApiResponse.success("회원가입에 성공했습니다.");
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("가입되지 않은 ID입니다."));

        boolean pwIsMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (pwIsMatch) {
            AccessToken accessToken = jwtTokenProvider.createAccessToken(loginRequestDto.getUserId(), Role.USER);
            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(loginRequestDto.getUserId(), Role.USER);

            Map<String, Object> result = new HashMap<>();
            result.put("accessToken", accessToken);
            result.put("refreshToken", refreshToken);
            result.put("userId", user.getUserId());
            result.put("name", user.getName());

            return ApiResponse.success(result);
        } else {
            return ApiResponse.badRequest("비밀번호가 일치하지 않습니다.");
        }
    }

    public ResponseEntity<ApiResponse<Object>> logout() {
        String accessToken = jwtTokenProvider.getUserIdFromCurrentRequest();
        ValueOperations<String, AccessToken> opsForValue = redisTemplateAccess.opsForValue();
        ValueOperations<String, RefreshToken> opsForValue2 = redisTemplateRefresh.opsForValue();
        ValueOperations<String, Object> objectVop = redisTemplateObject.opsForValue();

        String userId = null;

        try {
            userId = jwtTokenProvider.getIdFromToken(accessToken); // access_token에서 user_id를 가져옴(유효성 검사)
        } catch (IllegalArgumentException ignored) {
        } catch (ExpiredJwtException e) { // 만료
            userId = e.getClaims().getSubject(); // 만료된 access token으로부터 user_id를 가져옴
            log.info("user_id from expired access token : " + userId);
        }

        if (opsForValue.get("access_" + userId) != null) {
            redisTemplateAccess.delete("access_" + userId);
            objectVop.set(accessToken, true);
            redisTemplateObject.expire(accessToken, jwtProperty.getAccessTokenValidityDuration());
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

    @Transactional
    public ResponseEntity<ApiResponse<Object>> refresh(RefreshRequestDto dto) {
        if (!jwtTokenProvider.isRefreshTokenExpired(dto.getRefreshToken())) { // refresh token 만료되지 않았을때 -> 여기서 예외가 터짐
            String userId = jwtTokenProvider.getIdFromToken(dto.getRefreshToken()); // access_token에서 user_id 가져옴(유효성 검사)
            User user = userRepository.findById(userId).orElseThrow((() -> new NoSuchElementException("가입되지 않은 계정입니다.")));

            AccessToken newAccessToken = jwtTokenProvider.createAccessToken(user.getUserId(), Role.USER);

            return ApiResponse.success(newAccessToken);
        } else { // refresh 토큰 expire
            return ApiResponse.badRequest("refreshToken이 만료되었습니다. 다시 로그인해주세요.");
        }
    }
}
