package com.koke.koke_backend.roastery.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryListResponseDto;
import com.koke.koke_backend.roastery.enums.SortType;
import com.koke.koke_backend.roastery.service.RoasteryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "TOKEN")
@RequestMapping("/roastery")
public class RoasteryController {

    private final RoasteryService roasteryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoasteryListResponseDto>>> list(@RequestParam(name = "sort") SortType sortType) {
        return roasteryService.list(sortType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> detail(@ModelAttribute(name = "id") String id) {
        return roasteryService.detail(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody RoasteryCreateRequestDto roasteryCreateRequestDto) {
        return roasteryService.create(roasteryCreateRequestDto);
    }

    @GetMapping("/parse")
    public ResponseEntity<ApiResponse<Object>> parse() throws IOException {
        return roasteryService.parse();
    }

}
