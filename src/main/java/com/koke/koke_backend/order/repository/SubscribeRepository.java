package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long>, QSubscribeRepository {
    Optional<Subscribe> findByUuid(String uuid);
}
