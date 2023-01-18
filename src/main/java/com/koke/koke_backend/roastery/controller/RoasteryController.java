package com.koke.koke_backend.roastery.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryDetailResponseDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;
import com.koke.koke_backend.roastery.service.RoasteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
* @author : 김하빈(danny9643@naver.com)
* @description : Roastery API
* @!
* @?
* @TODO
* @Date : 2023-01-11, Wed, 22;1
*/

@Tag(name = "로스터리 관리", description = "로스터리 관리 API")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "TOKEN")
@RequestMapping("/roastery")
public class RoasteryController {

    private final RoasteryService roasteryService;

    @Tag(name = "로스터리 관리", description = "로스터리 관리 API")
    @Operation(summary = "로스터리 리스트 조회", description = "로스터리 리스트 조회 API")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoasteryListResponseDto>>> list(@RequestParam(name = "sort") SortType sortType) {
        return roasteryService.list(sortType);
    }

    @Tag(name = "로스터리 관리", description = "로스터리 관리 API")
    @Operation(summary = "로스터리 상세 조회", description = "로스터리 상세 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoasteryDetailResponseDto>> detail(@PathVariable(name = "id") String id) {
        return roasteryService.detail(id);
    }

    @Tag(name = "로스터리 관리", description = "로스터리 관리 API")
    @Operation(summary = "로스터리 생성", description = "로스터리 생성 API - 관리자전용")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@Valid @RequestBody RoasteryCreateRequestDto roasteryCreateRequestDto) {
        return roasteryService.create(roasteryCreateRequestDto);
    }

    @Tag(name = "로스터리 관리", description = "로스터리 관리 API")
    @Operation(summary = "로스터리 데이터 파싱 생성", description = "로스터리 데이터 파싱 생성 API")
    @GetMapping("/parse")
    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        return roasteryService.parse();
    }

}
