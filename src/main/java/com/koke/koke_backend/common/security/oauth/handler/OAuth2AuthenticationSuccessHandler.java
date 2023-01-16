package com.koke.koke_backend.common.security.oauth.handler;

import com.koke.koke_backend.common.security.jwt.AccessToken;
import com.koke.koke_backend.common.security.jwt.JwtTokenProvider;
import com.koke.koke_backend.common.security.jwt.RefreshToken;
import com.koke.koke_backend.common.security.jwt.Role;
import com.koke.koke_backend.common.security.oauth.info.OAuth2UserInfo;
import com.koke.koke_backend.common.security.oauth.info.OAuth2UserInfoFactory;
import com.koke.koke_backend.common.security.oauth.info.ProviderType;
import com.koke.koke_backend.common.security.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.koke.koke_backend.common.security.oauth.utils.CookieUtil;
import com.koke.koke_backend.common.yml.JwtProperty;
import com.koke.koke_backend.common.yml.OAuth2Property;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import static com.koke.koke_backend.common.security.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.koke.koke_backend.common.security.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;


@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtTokenProvider jwtTokenProvider;
	private final OAuth2Property oAuth2Property;
	private final JwtProperty jwtProperty;
	private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
				.map(Cookie::getValue);

		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
			throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
		}

		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

		OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
		ProviderType providerType = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

		OidcUser user = ((OidcUser) authentication.getPrincipal());
		OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
		Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

		Role role = hasAuthority(authorities, Role.ADMIN.getValue()) ? Role.ADMIN : Role.USER;

		AccessToken accessToken = jwtTokenProvider.createAccessToken(userInfo.getId(), role);
		RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(userInfo.getId(), role);

		int cookieMaxAge = jwtProperty.getRefreshTokenValidity().intValue() / 60;

		CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
		CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getRefreshToken(), cookieMaxAge);

		return UriComponentsBuilder.fromUriString(targetUrl)
				.queryParam("token", accessToken.getAccessToken())
				.build().toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
		if (authorities == null) {
			return false;
		}

		for (GrantedAuthority grantedAuthority : authorities) {
			if (authority.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);

		return oAuth2Property.getOauth2().getAuthorizedRedirectUris()
				.stream()
				.anyMatch(authorizedRedirectUri -> {
					// Only validate host and port. Let the clients use different paths if they want to
					URI authorizedURI = URI.create(authorizedRedirectUri);
					return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
							&& authorizedURI.getPort() == clientRedirectUri.getPort();
				});
	}
}
