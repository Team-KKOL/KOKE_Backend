package com.koke.koke_backend.user.service;

import com.koke.koke_backend.address.entity.AddressBook;
import com.koke.koke_backend.address.mapper.AddressBookMapper;
import com.koke.koke_backend.address.repository.AddressBookRepository;
import com.koke.koke_backend.cart.entity.Cart;
import com.koke.koke_backend.cart.mapper.CartMapper;
import com.koke.koke_backend.cart.repository.CartRepository;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.AccessToken;
import com.koke.koke_backend.common.security.JwtTokenProvider;
import com.koke.koke_backend.common.security.RefreshToken;
import com.koke.koke_backend.common.security.Role;
import com.koke.koke_backend.common.yml.JwtProperty;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.RefreshRequestDto;
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
                .map(user -> ApiResponse.badRequest("?????? ???????????? ????????? ID?????????."))
                .orElse(ApiResponse.success("?????? ????????? ????????? ID?????????."));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<Object>> checkEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> ApiResponse.badRequest("?????? ???????????? ??????????????????."))
                .orElse(ApiResponse.success("?????? ????????? ??????????????????."));
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> signUp(SignUpRequestDto signUpRequestDto) {
        User user = userMapper.signUpRequestDtoToUser(signUpRequestDto);
        userRepository.saveAndFlush(user);

        user = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new NoSuchElementException("???????????? ???????????? ????????????."));

        AddressBook addressBook = addressBookMapper.createAddressBook(user);
        addressBookRepository.save(addressBook);

        Cart cart = cartMapper.createCart(user);
        cartRepository.save(cart);

        return ApiResponse.success("??????????????? ??????????????????.");
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("???????????? ?????? ID?????????."));

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
            return ApiResponse.badRequest("??????????????? ???????????? ????????????.");
        }
    }

    public ResponseEntity<ApiResponse<Object>> logout() {
        HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
        ValueOperations<String, AccessToken> opsForValue = redisTemplateAccess.opsForValue();
        ValueOperations<String, RefreshToken> opsForValue2 = redisTemplateRefresh.opsForValue();
        ValueOperations<String, Object> objectVop = redisTemplateObject.opsForValue();

        String userId = null;
        String accessToken = jwtTokenProvider.resolveToken(request);

        try {
            userId = jwtTokenProvider.getIdFromToken(accessToken); // access_token?????? user_id??? ?????????(????????? ??????)
        } catch (IllegalArgumentException ignored) {
        } catch (ExpiredJwtException e) { // ??????
            userId = e.getClaims().getSubject(); // ????????? access token???????????? user_id??? ?????????
            log.info("user_id from expired access token : " + userId);
        }

        if (opsForValue.get("access_" + userId) != null) {
            redisTemplateAccess.delete("access_" + userId);
            objectVop.set(accessToken, true);
            redisTemplateObject.expire(accessToken, jwtProperty.getAccessTokenValidityDuration());
        }

        try {
            Object refreshTokenObject = opsForValue2.get("refresh_" + userId);
            if (refreshTokenObject != null) { // refresh token??? db??? ?????????????????????
                redisTemplateAccess.delete("refresh_" + userId); // refresh token??? ???????????? ??????
            }
        } catch (IllegalArgumentException e) {
            log.warn("user does not exist");
        }

        log.info(" logout ing : " + accessToken);

        return ApiResponse.success("???????????? ???????????????.");
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> refresh(RefreshRequestDto dto) {
        if (!jwtTokenProvider.isRefreshTokenExpired(dto.getRefreshToken())) { // refresh token ???????????? ???????????? -> ????????? ????????? ??????
            String userId = jwtTokenProvider.getIdFromToken(dto.getRefreshToken()); // access_token?????? user_id ?????????(????????? ??????)
            User user = userRepository.findById(userId).orElseThrow((() -> new NoSuchElementException("???????????? ?????? ???????????????.")));

            AccessToken newAccessToken = jwtTokenProvider.createAccessToken(user.getUserId(), Role.USER);

            return ApiResponse.success(newAccessToken);
        } else { // refresh ?????? expire
            return ApiResponse.badRequest("refreshToken??? ?????????????????????. ?????? ?????????????????????.");
        }
    }
}
