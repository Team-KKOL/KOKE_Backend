package com.koke.koke_backend.fav.repository;

import com.koke.koke_backend.fav.dto.FavProductDto;
import com.koke.koke_backend.fav.dto.FavRoasteryDto;
import com.koke.koke_backend.fav.entity.Fav;

import java.util.List;
import java.util.Optional;

public interface QFavRepository {
    Optional<Fav> getFavRoastery(String userId, String roasteryUuid);
    Optional<Fav> getFavProduct(String userId, String productUuid);
    List<FavRoasteryDto> favRoasteryList(String userId);
    List<FavProductDto> favProductList(String userId);
}
