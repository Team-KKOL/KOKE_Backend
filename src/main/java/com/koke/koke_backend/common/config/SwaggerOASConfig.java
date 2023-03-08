package com.koke.koke_backend.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;
import static java.time.LocalDate.now;

@Configuration
@RequiredArgsConstructor
public class SwaggerOASConfig implements WebMvcConfigurer {

    private static final String SCHEMA_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
    @Value("${custom.path}")
    private String uploadPath;
    private final ObjectMapper objectMapper;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("TOKEN", securityScheme()))
                .info(apiInfo()); // API 문서에 대한 정보 추가
    }

    private Info apiInfo() {
        return new Info()
                .title("KOKE Spring REST API Documentation (" + now() + ")")
                .description("KOKE Spring Backend REST API 명세서")
                .version("0.5");
    }

    @Bean
    public SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(HEADER)
                .name("TOKEN");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000", "http://localhost:9002",
                        "http://15.165.242.95:9002", "https://kkol.store", "https://api.kkol.store:9200")
                .exposedHeaders("TOKEN");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String sb = "file:" +
                uploadPath +
                "/";

        registry
                .addResourceHandler("/**")
                .addResourceLocations(sb);

        registry.addResourceHandler("/swagger-ui/index.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/", ".jsp");
    }

//    @Bean
//    public OpenApiCustomiser openAPICustomiser() {
//        return openApi -> {
//            Map<String, Schema> schemas = openApi.getComponents().getSchemas();
//            schemas.values().forEach(schema -> {
//                Map<String, Schema<?>> properties = schema.getProperties() != null ?
//                        schema.getProperties() : Map.of();
//
//                for (String propertyName : properties.keySet()) {
//                    Schema<?> propertySchema = properties.get(propertyName);
//                    if (propertySchema instanceof DateTimeSchema) {
//                        properties.replace(propertyName, new StringSchema()
//                                .example(propertySchema.getTitle())
//                                .pattern(SCHEMA_PATTERN)
//                                //copies original description
//                                .description(propertySchema.getDescription()));
//                    }
//                }
//            });
//        };
//    }

}
