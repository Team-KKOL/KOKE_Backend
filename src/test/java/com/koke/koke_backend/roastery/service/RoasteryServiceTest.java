package com.koke.koke_backend.roastery.service;

import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RoasteryServiceTest {

    @Autowired
    private RoasteryService roasteryService;

    @MockBean
    private RoasteryRepository roasteryRepository;

    @Test
    @DisplayName("로스터리 카페 리스트 조회 Query")
    void list() {
    }

    @Test
    @DisplayName("로스터리 카페 상세 조회 Query")
    void detail() {
    }
}
