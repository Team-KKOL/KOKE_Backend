package com.koke.koke_backend.celebrity.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.celebrity.dto.json.CelebrityCommentDataDto;
import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import com.koke.koke_backend.celebrity.mapper.CelebrityCommentMapper;
import com.koke.koke_backend.celebrity.repository.CelebrityCommentRepository;
import com.koke.koke_backend.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CelebrityCommentService {

    private final CelebrityCommentRepository celebrityCommentRepository;
    private final CelebrityCommentMapper celebrityCommentMapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<CelebrityComment>>> list() {
        List<CelebrityComment> findAll = celebrityCommentRepository.findAll();
        return ApiResponse.success(findAll);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        File file = new ClassPathResource("celeb.json").getFile();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);

        List<CelebrityCommentDataDto> celebrityCommentDtos = objectMapper.readValue(file, new TypeReference<>() {});
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(celebrityCommentDtos));

        List<CelebrityComment> list = celebrityCommentMapper.toEntityList(celebrityCommentDtos);
        celebrityCommentRepository.saveAll(list);

        return ApiResponse.success();
    }

}
