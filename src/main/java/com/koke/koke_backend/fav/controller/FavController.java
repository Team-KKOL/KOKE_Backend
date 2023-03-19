package com.koke.koke_backend.fav.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.CurrentUser;
import com.koke.koke_backend.fav.dto.FavProductDto;
import com.koke.koke_backend.fav.dto.FavRoasteryDto;
import com.koke.koke_backend.fav.service.FavService;
import com.koke.koke_backend.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "좋아요 관리", description = "좋아요 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fav")
public class FavController {

    private final FavService favService;

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "로스터리 좋아요", description = "로스터리 좋아요 API")
    @SecurityRequirement(name = "TOKEN")
    @PutMapping("/roastery/{id}")
    public ResponseEntity<ApiResponse<Object>> saveFavRoastery(@CurrentUser User current, @PathVariable(name = "id") String roasteryId) {
        return favService.saveFavRoastery(current, roasteryId);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "로스터리 좋아요 조회", description = "로스터리 좋아요 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping("/roastery")
    public ResponseEntity<ApiResponse<List<FavRoasteryDto>>> getFavRoastery(@CurrentUser User current) {
        return favService.getFavRoastery(current);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "커피 좋아요", description = "커피 좋아요 API")
    @SecurityRequirement(name = "TOKEN")
    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Object>> saveFavProduct(@CurrentUser User current, @PathVariable(name = "id") String productId) {
        return favService.saveFavProduct(current, productId);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "커피 좋아요 조회", description = "커피 좋아요 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping("/product")
    public ResponseEntity<ApiResponse<List<FavProductDto>>> getFavProduct(@CurrentUser User current) {
        return favService.getFavProduct(current);
    }

}
