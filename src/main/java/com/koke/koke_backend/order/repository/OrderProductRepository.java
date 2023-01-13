package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>, QOrderProductRepository {
}
