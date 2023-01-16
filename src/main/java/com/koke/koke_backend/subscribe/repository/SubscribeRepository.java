package com.koke.koke_backend.subscribe.repository;

import com.koke.koke_backend.subscribe.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, String>, QSubscribeRepository {
}
