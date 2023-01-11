package com.koke.koke_backend;

import com.koke.koke_backend.common.initializer.EncryptInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KokeBackendApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(KokeBackendApplication.class)
                .initializers(new EncryptInitializer())
                .run(args);
    }

}
