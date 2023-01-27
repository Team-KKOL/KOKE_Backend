package com.koke.koke_backend.user.controller;

import com.koke.koke_backend.AbstractIntegrationTest;
import com.koke.koke_backend.user.dto.LoginRequestDto;
import com.koke.koke_backend.user.dto.SignUpRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AuthControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp0() {

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(4)
    @DisplayName("아이디 중복확인 API - 성공")
    void checkUserIdSuccess() {
    }

    @Test
    @Order(5)
    @DisplayName("아이디 중복확인 API - 실패")
    void checkUserIdFail() {
    }

    @Test
    @Order(6)
    @DisplayName("이메일 중복확인 API - 성공")
    void checkEmailSuccess() {
    }

    @Test
    @Order(7)
    @DisplayName("이메일 중복확인 API - 실패")
    void checkEmailFail() {
    }

    @Test
    @Order(1)
    @DisplayName("회원가입 API")
    void signUp() throws Exception {
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .userId("habin123")
                .password("tyuiop90()")
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
                        jsonPath("$.message").value("회원가입에 성공했습니다.")
                )
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("로그인 API - 성공")
    void loginSuccess() throws Exception {
        String userId = "habin123";
        LoginRequestDto dto = LoginRequestDto.builder()
                .userId(userId)
                .password("tyuiop90()")
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
                        jsonPath("$.body.accessToken.userId").value(userId),
                        jsonPath("$.body.refreshToken").isNotEmpty(),
                        jsonPath("$.body.refreshToken.userId").value(userId)
                )
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("로그인 API - 실패")
    void loginFail() {

    }

    @Test
    @Order(8)
    @DisplayName("로그아웃 API")
    void logout() {
    }

    @Test
    @Order(9)
    @DisplayName("Access Token 재발급 API")
    void refresh() {
    }
}
