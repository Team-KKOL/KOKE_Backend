package com.koke.koke_backend.cart.repository;

import com.koke.koke_backend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, QCartRepository {
}
