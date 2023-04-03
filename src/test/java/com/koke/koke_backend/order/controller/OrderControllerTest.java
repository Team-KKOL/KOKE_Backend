package com.koke.koke_backend.order.controller;

import com.koke.koke_backend.base.ControllerBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class OrderControllerTest extends ControllerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("주문 요청 성공")
    void createOrder() {



    }

    @Test
    void orderList() {
    }

    @Test
    void createSubscribe() {
    }

    @Test
    void subscribeList() {
    }
}
