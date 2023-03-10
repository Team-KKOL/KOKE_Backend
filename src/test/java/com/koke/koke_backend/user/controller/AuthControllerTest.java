package com.koke.koke_backend.user.controller;

import com.koke.koke_backend.base.AbstractIntegrationTest;
import com.koke.koke_backend.common.security.AccessToken;
import com.koke.koke_backend.common.security.RefreshToken;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.RefreshRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class AuthControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedisTemplate<String, AccessToken> redisTemplateAccess;

    @Autowired
    private RedisTemplate<String, RefreshToken> redisTemplateRefresh;

    private static final String USER_ID = "habin123";
    private static final String PASSWORD = "tyuiop90()";
    private static final String EMAIL = "habin123@naver.com";

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @Order(5)
    @DisplayName("????????? ???????????? API - ??????")
    void checkUserIdSuccess() throws Exception {
        mockMvc.perform(
                        get("/check/id").param("userId", "habin1234")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("?????? ????????? ????????? ID?????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(6)
    @DisplayName("????????? ???????????? API - ??????")
    void checkUserIdFail() throws Exception {
        mockMvc.perform(
                        get("/check/id").param("userId", USER_ID)
                )
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.message").value("?????? ???????????? ????????? ID?????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(7)
    @DisplayName("????????? ???????????? API - ??????")
    void checkEmailSuccess() throws Exception {
        mockMvc.perform(
                        get("/check/email").param("email", "danny964@naver.com")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("?????? ????????? ??????????????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(8)
    @DisplayName("????????? ???????????? API - ??????")
    void checkEmailFail() throws Exception {
        mockMvc.perform(
                        get("/check/email").param("email", EMAIL)
                )
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.message").value("?????? ???????????? ??????????????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(1)
    @DisplayName("???????????? API")
    void signUp() throws Exception {
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .userId(USER_ID)
                .password(PASSWORD)
                .name("habin")
                .email("habin123@naver.com")
                .phoneNo("010-1234-5678")
                .build();

        mockMvc.perform(
                        post("/signUp")
                                .contentType("application/json")
                                .content(toJson(dto))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("??????????????? ??????????????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("????????? API - ??????")
    void loginSuccess() throws Exception {
        LoginRequestDto dto = LoginRequestDto.builder()
                .userId(USER_ID)
                .password(PASSWORD)
                .build();

        mockMvc.perform(
                        post("/login")
                                .contentType("application/json")
                                .content(toJson(dto))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("SUCCESS"),
                        jsonPath("$.body.accessToken").isNotEmpty(),
                        jsonPath("$.body.accessToken.userId").value(USER_ID),
                        jsonPath("$.body.refreshToken").isNotEmpty(),
                        jsonPath("$.body.refreshToken.userId").value(USER_ID)
                )
                .andDo(print());

        ValueOperations<String, AccessToken> opsAccess = redisTemplateAccess.opsForValue();
        AccessToken accessToken = opsAccess.get("access_" + USER_ID);
        assertNotNull(accessToken, "Redis?????? ????????? AccessToken??? null?????? ??????");

        ValueOperations<String, RefreshToken> opsRefresh = redisTemplateRefresh.opsForValue();
        RefreshToken refreshToken = opsRefresh.get("refresh_" + USER_ID);
        assertNotNull(refreshToken, "Redis?????? ????????? RefreshToken??? null?????? ??????");
    }

    @Test
    @Order(3)
    @DisplayName("????????? API - ??????1 (???????????? ?????? ?????????)")
    void loginFail1() throws Exception {
        String userId = "habin123111";
        LoginRequestDto dto = LoginRequestDto.builder()
                .userId(userId)
                .password(PASSWORD)
                .build();

        mockMvc.perform(
                        post("/login")
                                .contentType("application/json")
                                .content(toJson(dto))
                )
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.message").value("???????????? ?????? ID?????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("????????? API - ??????2 (???????????? ?????????)")
    void loginFail2() throws Exception {
        LoginRequestDto dto = LoginRequestDto.builder()
                .userId(USER_ID)
                .password("tyuiop90()1")
                .build();

        mockMvc.perform(
                        post("/login")
                                .contentType("application/json")
                                .content(toJson(dto))
                )
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.message").value("??????????????? ???????????? ????????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("???????????? API")
    void logout() throws Exception {
        ValueOperations<String, AccessToken> opsAccess = redisTemplateAccess.opsForValue();
        AccessToken accessToken = opsAccess.get("access_" + USER_ID);
        assertNotNull(accessToken, "Redis?????? ????????? AccessToken??? null?????? ??????");

        mockMvc.perform(
                        get("/logoutUser")
                                .header("Authorization", String.format("Bearer %s", accessToken.getAccessToken()))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("???????????? ???????????????.")
                )
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("Access Token ????????? API - ??????")
    void refresh() throws Exception {
        ValueOperations<String, RefreshToken> opsRefresh = redisTemplateRefresh.opsForValue();
        RefreshToken refreshToken = opsRefresh.get("refresh_" + USER_ID);
        assertNotNull(refreshToken, "Redis?????? ????????? AccessToken??? null?????? ??????");

        RefreshRequestDto dto = RefreshRequestDto.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .build();

        mockMvc.perform(
                        post("/refresh")
                                .contentType("application/json")
                                .content(toJson(dto))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value("SUCCESS"),
                        jsonPath("$.body").isNotEmpty()
                )
                .andDo(print());
    }
}
