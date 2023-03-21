package com.koke.koke_backend.dummy;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.KokeBackendApplication;
import com.koke.koke_backend.celebrity.dto.json.CelebrityCommentDataDto;
import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import com.koke.koke_backend.celebrity.mapper.CelebrityCommentMapper;
import com.koke.koke_backend.celebrity.repository.CelebrityCommentRepository;
import com.koke.koke_backend.common.initializer.EncryptInitializer;
import com.koke.koke_backend.product.dto.json.ProductDataDto;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.product.mapper.ProductMapper;
import com.koke.koke_backend.product.repository.ProductRepository;
import com.koke.koke_backend.roastery.dto.json.RoasteryDataDto;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.roastery.mapper.RoasteryMapper;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@ActiveProfiles("test")
@SpringBootTest(
        classes = KokeBackendApplication.class,
        args = {"--encrypt=123"}
)
@ContextConfiguration(initializers = EncryptInitializer.class)
class DummyService {

    @Autowired
    private RoasteryRepository roasteryRepository;

    @Autowired
    private RoasteryMapper roasteryMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CelebrityCommentRepository celebrityCommentRepository;

    @Autowired
    private CelebrityCommentMapper celebrityCommentMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
    }

    @DisplayName("로스터리 데이터 파싱 저장")
    @Test
    @Transactional
//    @Rollback(false)
    void defaultRoasteryData() throws IOException {
        File file = new ClassPathResource("roastery.json").getFile();
        List<RoasteryDataDto> roasteryDataDtos = objectMapper.readValue(file, new TypeReference<>() {
        });
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roasteryDataDtos));

        List<Roastery> list = roasteryMapper.toEntityList(roasteryDataDtos);
        roasteryRepository.saveAll(list);
    }

    @DisplayName("커피 데이터 파싱 저장")
    @Test
    @Transactional
//    @Rollback(false)
    void defaultProductData() throws IOException {
        File file = new ClassPathResource("product.json").getFile();

        List<ProductDataDto> productDataDtos = objectMapper.readValue(file, new TypeReference<>() {
        });
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productDataDtos));

        List<Product> list = productMapper.toEntityList(productDataDtos);
        productRepository.saveAll(list);
    }

    @DisplayName("셀럽 추천사 데이터 파싱 저장")
    @Test
    @Transactional
//    @Rollback(false)
    void defaultCelebrityCommentData() throws IOException {
        File file = new ClassPathResource("celeb.json").getFile();
        List<CelebrityCommentDataDto> celebrityCommentDtos = objectMapper.readValue(file, new TypeReference<>() {
        });
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(celebrityCommentDtos));

        List<CelebrityComment> list = celebrityCommentMapper.toEntityList(celebrityCommentDtos);
        celebrityCommentRepository.saveAll(list);
    }

}
