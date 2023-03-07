package com.koke.koke_backend.doc;

import com.koke.koke_backend.common.exception.GlobalExceptionHandler;
import com.koke.koke_backend.common.util.JsonUtils;
import com.koke.koke_backend.common.util.LocalDateTimeUtils;
import com.koke.koke_backend.common.util.LocalDateUtils;
import com.koke.koke_backend.common.util.LocalTimeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MockMvcFactory {

    public static MockMvc getMockMvc(Object... controllers) {
        return getMockMvcBuilder(controllers).build();
    }

    private static StandaloneMockMvcBuilder getMockMvcBuilder(Object... controllers) {
        var conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new LocalDateTimeConverter());
        conversionService.addConverter(new LocalDateConverter());
        conversionService.addConverter(new LocalTimeConverter());

        return MockMvcBuilders.standaloneSetup(controllers)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setConversionService(conversionService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(JsonUtils.getMapper()))
                .addFilter(new CharacterEncodingFilter(UTF_8.name(), true));
    }

    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(@NotNull String source) {
            return LocalDateTimeUtils.toLocalDateTime(source);
        }
    }

    public static class LocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(@NotNull String source) {
            return LocalDateUtils.toLocalDate(source);
        }
    }

    public static class LocalTimeConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(@NotNull String source) {
            return LocalTimeUtils.toLocalTime(source);
        }
    }

}
