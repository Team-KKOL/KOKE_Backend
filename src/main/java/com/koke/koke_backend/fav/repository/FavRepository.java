package com.koke.koke_backend.fav.repository;

import com.koke.koke_backend.fav.entity.Fav;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavRepository extends JpaRepository<Fav, Long>, QFavRepository {
}
