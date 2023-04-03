package com.koke.koke_backend.common.config;

import com.koke.koke_backend.common.initializer.SSHTunnelInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Slf4j
@Profile({"local", "local_v2"})
@Configuration
@RequiredArgsConstructor
public class SSHDataSourceConfig {

    private final SSHTunnelInitializer initializer;

    @Bean("dataSource")
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        Integer forwardedPort = initializer.buildSSHConnection();
        String url = String.format("jdbc:mariadb://localhost:%d/%s", forwardedPort, initializer.getDatabaseName());
        log.info("JDBC Connection Url: " + url);

        return DataSourceBuilder.create()
                .url(url)
                .username(properties.getUsername())
                .password(properties.getPassword())
                .driverClassName(properties.getDriverClassName())
                .build();
    }

}
