package com.koke.koke_backend.cart.service;

import com.koke.koke_backend.cart.repository.CartProductRepository;
import com.koke.koke_backend.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

}
