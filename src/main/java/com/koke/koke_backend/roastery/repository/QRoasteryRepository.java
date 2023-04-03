package com.koke.koke_backend.roastery.repository;

import com.koke.koke_backend.roastery.dto.RoasteryDetailResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryTop4ResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;

import java.util.List;
import java.util.Optional;

public interface QRoasteryRepository {
    List<RoasteryListResponseDto> list(SortType sortType);

    Optional<RoasteryDetailResponseDto> detail(String uuid);

    List<RoasteryTop4ResponseDto> top4();
}
