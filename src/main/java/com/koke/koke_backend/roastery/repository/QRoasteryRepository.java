package com.koke.koke_backend.roastery.repository;

import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;

import java.util.List;

public interface QRoasteryRepository {
    List<RoasteryListResponseDto> list(SortType sortType);
}
