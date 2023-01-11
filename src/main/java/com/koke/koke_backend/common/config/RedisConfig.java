package com.koke.koke_backend.common.config;

import com.koke.koke_backend.common.security.jwt.AccessToken;
import com.koke.koke_backend.common.security.jwt.RefreshToken;
import io.lettuce.core.RedisURI;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisConfig {

	private final RedisProperties redisProperties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisURI redisURI = RedisURI.builder()
				.withHost(redisProperties.getHost())
				.withPort(redisProperties.getPort())
				.withDatabase(redisProperties.getDatabase())
				.withAuthentication(redisProperties.getUsername(), redisProperties.getPassword())
				.build();
		RedisConfiguration redisConfiguration = LettuceConnectionFactory.createRedisConfiguration(redisURI);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration);
		lettuceConnectionFactory.afterPropertiesSet();
		return lettuceConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, AccessToken> redisTemplateForAccessToken() {
		RedisTemplate<String, AccessToken> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(AccessToken.class));
		return redisTemplate;
	}

	@Bean
	public RedisTemplate<String, RefreshToken> redisTemplateForRefreshToken() {
		RedisTemplate<String, RefreshToken> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(RefreshToken.class));
		return redisTemplate;
	}

}
