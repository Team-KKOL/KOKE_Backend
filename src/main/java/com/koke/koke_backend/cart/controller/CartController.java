package com.koke.koke_backend.cart.controller;

import com.koke.koke_backend.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author : 김하빈(danny9643@naver.com)
* @description : 장바구니 API
* @!
* @?
* @TODO
* @Date : 2023-01-11, Wed, 22;0
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

}
