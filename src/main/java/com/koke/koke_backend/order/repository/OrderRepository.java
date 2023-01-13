package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String>, QOrderRepository {
}
