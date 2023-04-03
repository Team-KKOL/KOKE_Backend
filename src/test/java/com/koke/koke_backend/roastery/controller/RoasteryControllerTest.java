package com.koke.koke_backend.roastery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koke.koke_backend.base.ControllerBaseTest;
import com.koke.koke_backend.dummy.DummyService;
import com.koke.koke_backend.roastery.enums.SortType;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoasteryControllerTest extends ControllerBaseTest {

    @Autowired
    private DummyService dummyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws IOException {
        dummyService.defaultRoasteryData();
    }

    @Test
    @Order(1)
    @DisplayName("로스터리 카페 리스트 조회 API - 이름순")
    void list() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/roastery")
                                .param("sort", String.valueOf(SortType.NAME))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body[0].roasteryNm").value("180커피로스터스"),
                        jsonPath("$.body[0].logoImgUrl").value("https://d2wosiipoa41qn.cloudfront.net/j5QPmenjGBbECz8galnFurmJXBQ=/200x200/s3.ap-northeast-2.amazonaws.com/koke-uploads/images/20220718/696e87d2b1c6458b9a8b0882a7184997.jpeg")
                )
                .andDo(print())
                .andReturn();

        String actualJson = result.getResponse().getContentAsString();
//        Map<String, String> map = objectMapper.readValue(actualJson, new TypeReference<HashMap<String, String>>() {});

    }

    @Test
    @Order(2)
    @DisplayName("로스터리 상위 4개 조회")
    void top4() throws Exception {
        mockMvc.perform(
                        get("/roastery/top4")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body.length()").value(4)
                )
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("로스터리 카페 상세 조회 API")
    void detail() throws Exception {
//        mockMvc.perform(
//                        get("/roastery/{id}", "")
//                )
//                .andExpectAll(
//                        status().isOk()
//                )
//                .andDo(print());
    }


}
