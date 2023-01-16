package com.koke.koke_backend.common.yml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "oauth2")
@NoArgsConstructor
@Getter
@Setter
public class OAuth2Property {

    private final OAuth2 oauth2 = new OAuth2();

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris;

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

}
