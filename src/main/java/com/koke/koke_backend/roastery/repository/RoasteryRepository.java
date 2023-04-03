package com.koke.koke_backend.roastery.repository;

import com.koke.koke_backend.roastery.entity.Roastery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoasteryRepository extends JpaRepository<Roastery, String>, QRoasteryRepository {
    Optional<Roastery> findByUuid(String uuid);
    Roastery findByName(String name);
}
