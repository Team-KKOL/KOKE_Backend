package com.koke.koke_backend.cart.repository;

import com.koke.koke_backend.cart.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long>, QCartProductRepository {
}
