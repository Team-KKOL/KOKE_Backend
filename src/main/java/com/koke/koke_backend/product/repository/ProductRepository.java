package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QProductRepository {
    Optional<Product> findByUuid(String uuid);
}
