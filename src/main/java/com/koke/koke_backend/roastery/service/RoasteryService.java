package com.koke.koke_backend.roastery.service;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoasteryService {

    private final RoasteryRepository roasteryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<List<RoasteryListResponseDto>>> list(SortType sortType) {
        List<RoasteryListResponseDto> list = roasteryRepository.list(sortType);
        return ApiResponse.success(list);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse<?>> detail(String id) {



        return null;
    }

    @Transactional
    public ResponseEntity<ApiResponse<?>> create(RoasteryCreateRequestDto roasteryCreateRequestDto) {
        return null;
    }
}
