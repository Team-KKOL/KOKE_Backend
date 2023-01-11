package com.koke.koke_backend.common.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.common.dto.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author : 김하빈(hbkim@bpnsolution.com)
 * @description : Jwt 인증 진입점 클래스
 * @Date : 2020. 10. 7.
 * @Time : 오전 9:09:52
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException, InsufficientAuthenticationException {
        log.error("Unauthorized error: {}", authException.getMessage());
        log.error("", authException);

        response.setCharacterEncoding("UTF-8");
        response.setContentType(APPLICATION_JSON_VALUE + ";charset=UTF-8");
        response.setStatus(SC_UNAUTHORIZED);


        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper om = new ObjectMapper();
            om.writeValue(os, ApiResponse.unauthorized("권한이 없습니다. 로그인해주세요."));
            os.flush();
        }
    }
}
