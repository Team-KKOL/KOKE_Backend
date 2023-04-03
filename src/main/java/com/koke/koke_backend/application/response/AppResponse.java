package com.koke.koke_backend.application.response;

import com.koke.koke_backend.common.exception.ExceptionResponseMapper;
import com.koke.koke_backend.common.util.MessageSourceUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@AllArgsConstructor
public class AppResponse {

    private final MessageSourceUtil messageSourceUtil;

    public <T> ResponseEntity<ResponseMapper<?>> ok() {
        return ResponseEntity.ok().body(new ResponseMapper<>(MessageCode.SUCCESS.name(), null));
    }

    private <T> ResponseEntity<ResponseMapper<?>> ok(final MessageCode messageCode, final T source) {
        return ResponseEntity.ok().body(new ResponseMapper<>(messageCode.name(), source));
    }

    public <T> ResponseEntity<ResponseMapper<?>> ok(final T source) {
        return ok(MessageCode.SUCCESS, source);
    }

    public <T, D> ResponseEntity<ResponseMapper<?>> created(final T source, final String uri) throws URISyntaxException {
        URI resultUri = new URI(uri);
        return ResponseEntity.created(resultUri).body(new ResponseMapper<>(MessageCode.SUCCESS.name(), source));
    }

    public ResponseEntity<ExceptionResponseMapper> error(final MessageCode messageCode) {
        return ResponseEntity
                .status(messageCode.getHttpStatus())
                .body(new ExceptionResponseMapper(messageSourceUtil.getMessage(messageCode.getCode()), messageCode.getCode()));
    }
}
