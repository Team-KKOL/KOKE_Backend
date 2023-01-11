package com.koke.koke_backend.common.config;

import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.introspector.BuilderArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixtureMonkeyConfig {

    @Bean
    public LabMonkey labMonkey() {
        return LabMonkey.labMonkeyBuilder()
                .objectIntrospector(BuilderArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();
    }

}
