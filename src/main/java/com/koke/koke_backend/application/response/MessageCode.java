package com.koke.koke_backend.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MessageCode {

    SUCCESS(HttpStatus.OK, "0000"),
    CREATED(HttpStatus.CREATED, "0001"),
    ACCEPTED(HttpStatus.ACCEPTED, "0002");

    private final HttpStatus httpStatus;

    private final String code;

    public static Optional<MessageCode> get(String name) {
        return Arrays.stream(MessageCode.values())
                .filter(mc -> mc.name().equals(name))
                .findFirst();
    }

}
