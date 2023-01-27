package com.koke.koke_backend.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.KokeBackendApplication;
import com.koke.koke_backend.common.initializer.EncryptInitializer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(
        classes = KokeBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        args = {"--encrypt=123"}
)
@ContextConfiguration(initializers = EncryptInitializer.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> String toJson(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

    public static final String MARIA_DB_IMAGE_NAME = "mariadb:latest";
    @Container
    public static final MariaDBContainer<?> MARIA_DB_CONTAINER =
            new MariaDBContainer<>(DockerImageName.parse(MARIA_DB_IMAGE_NAME))
                    .withEnv("TZ", "Asia/Seoul")
                    .withEnv("MARIADB_DATABASE", "test")
                    .withEnv("MARIADB_USER", "test")
                    .withEnv("MARIADB_PASSWORD", "test")
                    .withCommand(
                            "--character-set-server=utf8mb4",
                            "--collation-server=utf8mb4_unicode_ci",
                            "--skip-character-set-client-handshake",
                            "--default-time-zone=+09:00"
                    )
                    .withReuse(true);

    @Container
    public static final GenericContainer<?> REDIS_CONTAINER =
            new GenericContainer<>(DockerImageName.parse("redis:latest"))
                    .withEnv("TZ", "Asia/Seoul")
                    .withExposedPorts(6379)
//                    .withCommand("redis-server --port 6389")
                    .withReuse(true);

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        MARIA_DB_CONTAINER.start();
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:tc:%s:///%s", MARIA_DB_IMAGE_NAME, MARIA_DB_CONTAINER.getDatabaseName()));
        registry.add("spring.datasource.username", MARIA_DB_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MARIA_DB_CONTAINER::getPassword);

        REDIS_CONTAINER.start();
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379).toString());
        registry.add("spring.data.redis.database", () -> 0);
    }


}
