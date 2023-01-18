package com.koke.koke_backend.roastery.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryDataDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.roastery.enums.SortType;
import com.koke.koke_backend.roastery.mapper.RoasteryMapper;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
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
public class RoasteryService {

    private final RoasteryRepository roasteryRepository;
    private final RoasteryMapper roasteryMapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<RoasteryListResponseDto>>> list(SortType sortType) {
        List<RoasteryListResponseDto> list = roasteryRepository.list(sortType);
        return ApiResponse.success(list);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<?>> detail(String id) {
        return null;
    }

    @Transactional
    public ResponseEntity<ApiResponse<?>> create(RoasteryCreateRequestDto roasteryCreateRequestDto) {
        return null;
    }

    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        File file = new ClassPathResource("roasterys.json").getFile();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);

        List<RoasteryDataDto> roasteryDataDtos = objectMapper.readValue(file, new TypeReference<>() {});
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roasteryDataDtos));

        List<Roastery> list = roasteryMapper.toEntityList(roasteryDataDtos);
        roasteryRepository.saveAll(list);

        return ApiResponse.success();
    }

}
