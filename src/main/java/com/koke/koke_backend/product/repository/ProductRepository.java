package com.koke.koke_backend.product.repository;

import com.koke.koke_backend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>, QProductRepository {
}
