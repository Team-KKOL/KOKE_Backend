package com.koke.koke_backend.application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

public class StringUtils {

    public String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

    public String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(object);
    }

    public String objectToPrettyJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public boolean hasText(String text) {
        return org.springframework.util.StringUtils.hasText(text);
    }

    final char[] passwordCharSet = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public String randomString(int size) {
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; i++) {
            sb.append(passwordCharSet[sr.nextInt(passwordCharSet.length)]);
        }

        return sb.toString();
    }

}
