package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, String>, QSubscribeRepository {
}
