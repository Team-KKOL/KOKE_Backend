package com.koke.koke_backend.category.repository;

import com.koke.koke_backend.category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>, QProductCategoryRepository {
}
