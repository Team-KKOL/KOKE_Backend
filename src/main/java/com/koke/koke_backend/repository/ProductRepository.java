package com.koke.koke_backend.repository;

import com.koke.koke_backend.entity.Product;
import com.koke.koke_backend.repository.querydsl.QProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>, QProductRepository {
}
