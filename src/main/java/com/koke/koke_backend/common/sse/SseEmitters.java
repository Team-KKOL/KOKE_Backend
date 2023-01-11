package com.koke.koke_backend.common.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SseEmitters {

    // Thread-Safe한 HashMap
    private final ConcurrentHashMap<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public SseEmitter add(String did, SseEmitter emitter) {
        this.emitterMap.put(did, emitter);

        log.info("new emitter added: {}", emitter);
        log.info("emitter Map size: {}", emitterMap.entrySet().size());

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitter.complete();
            this.emitterMap.remove(did); // 만료되면 리스트에서 삭제
        });

        emitter.onError((e) -> {
            log.error("SseEmitter Error : ", e);
            this.emitterMap.remove(did);
        });

        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

}

