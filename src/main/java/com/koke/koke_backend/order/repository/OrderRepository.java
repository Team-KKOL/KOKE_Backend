package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long>, QOrderRepository {
}
