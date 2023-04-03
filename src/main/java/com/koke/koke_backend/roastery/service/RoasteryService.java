package com.koke.koke_backend.roastery.service;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryDetailResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryTop4ResponseDto;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.roastery.enums.SortType;
import com.koke.koke_backend.roastery.mapper.RoasteryMapper;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoasteryService {

    private final RoasteryRepository roasteryRepository;
    private final RoasteryMapper roasteryMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<List<RoasteryListResponseDto>>> list(SortType sortType) {
        List<RoasteryListResponseDto> list = roasteryRepository.list(sortType);
        return ResponseMapper.success(list);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<List<RoasteryTop4ResponseDto>>> top4() {
        List<RoasteryTop4ResponseDto> list = roasteryRepository.top4();
        return ResponseMapper.success(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<RoasteryDetailResponseDto>> detail(String uuid) {
        Optional<RoasteryDetailResponseDto> detailOptional = roasteryRepository.detail(uuid);
        RoasteryDetailResponseDto detail =
                detailOptional.orElseThrow(() -> new IllegalArgumentException("해당 로스터리가 존재하지 않습니다."));

        return ResponseMapper.success(detail);
    }

    @Transactional
    public ResponseEntity<ResponseMapper<Object>> create(RoasteryCreateRequestDto dto) {
        Roastery roastery = roasteryMapper.createDtoToEntity(dto);
        roasteryRepository.save(roastery);
        return ResponseMapper.success();
    }
}
