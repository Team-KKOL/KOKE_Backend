package com.koke.koke_backend.roastery.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute != null ? objectMapper.writeValueAsString(attribute) : null;
    }

    @SneakyThrows
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData != null ? objectMapper.readValue(dbData, new TypeReference<>() {}) : null;
    }
}
