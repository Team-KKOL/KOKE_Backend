package com.koke.koke_backend.module.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	@Value("${custom.path}")
	private String uploadPath;
	private final ObjectMapper objectMapper;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("*")
				.exposedHeaders("TOKEN");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String sb = "file:" +
				uploadPath +
				"/";

		registry
				.addResourceHandler("/mdpt_file/**")
				.addResourceLocations(sb);
	}

}
