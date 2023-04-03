package com.koke.koke_backend.common.jpa;

import com.koke.koke_backend.common.security.EncryptUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Converter
@RequiredArgsConstructor
public class EncryptConverter implements AttributeConverter<String, String> {

    private final EncryptUtil encryptUtil;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (StringUtils.hasText(attribute)) {
            return encryptUtil.encrypt(attribute);
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (StringUtils.hasText(dbData)) {
            return encryptUtil.decrypt(dbData);
        }
        return null;
    }
}
