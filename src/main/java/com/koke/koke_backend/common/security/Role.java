package com.koke.koke_backend.common.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {
	ADMIN(ROLES.ADMIN),
	USER(ROLES.USER);

	public static class ROLES {
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String USER = "ROLE_USER";
	}

	public final String value;

	@Override
	public String getAuthority() {
		return value;
	}
}

