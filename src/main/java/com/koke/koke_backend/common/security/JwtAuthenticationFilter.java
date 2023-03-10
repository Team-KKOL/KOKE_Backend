package com.koke.koke_backend.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.common.dto.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.OutputStream;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	// Request로 들어오는 JWT Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(request);

		if (token != null && !jwtTokenProvider.isAccessTokenExpired(token)) {
			Authentication auth = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		if (token != null && jwtTokenProvider.isAccessTokenExpired(token)) {
			ObjectMapper om = new ObjectMapper();
			ResponseEntity<ApiResponse<Object>> apiResponse =
					ApiResponse.forbidden("accessToken이 만료되었습니다. accessToken 재발급을 진행해주세요.");

			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
			response.setStatus(SC_FORBIDDEN);
			try (OutputStream os = response.getOutputStream()) {
				om.writeValue(os, apiResponse);
				os.flush();
			}
		}

		filterChain.doFilter(request, response);
	}

}
