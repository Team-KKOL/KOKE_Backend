package com.koke.koke_backend.fav.controller;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.common.config.Uris;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "좋아요 관리", description = "좋아요 관리 API")
@RestController
@RequiredArgsConstructor
public class FavController {

    private final FavService favService;

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "로스터리 좋아요", description = "로스터리 좋아요 API")
    @SecurityRequirement(name = "TOKEN")
    @PutMapping(value = Uris.FAV_ROOT + Uris.ROASTERY_ROOT + Uris.REST_NAME_UUID)
    public ResponseEntity<ResponseMapper<Object>> saveFavRoastery(@CurrentUser User current, @PathVariable(name = "uuid") String roasteryUuid) {
        return favService.saveFavRoastery(current, roasteryUuid);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "로스터리 좋아요 조회", description = "로스터리 좋아요 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(Uris.FAV_ROOT + Uris.ROASTERY_ROOT)
    public ResponseEntity<ResponseMapper<List<FavRoasteryDto>>> getFavRoastery(@CurrentUser User current) {
        return favService.getFavRoastery(current);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "커피 좋아요", description = "커피 좋아요 API")
    @SecurityRequirement(name = "TOKEN")
    @PutMapping(value = Uris.FAV_ROOT + Uris.PRODUCT_ROOT + Uris.REST_NAME_UUID)
    public ResponseEntity<ResponseMapper<Object>> saveFavProduct(@CurrentUser User current, @PathVariable(name = "uuid") String productUuid) {
        return favService.saveFavProduct(current, productUuid);
    }

    @Tag(name = "좋아요 관리", description = "좋아요 관리 API")
    @Operation(summary = "커피 좋아요 조회", description = "커피 좋아요 조회 API")
    @SecurityRequirement(name = "TOKEN")
    @GetMapping(value = Uris.FAV_ROOT + Uris.PRODUCT_ROOT)
    public ResponseEntity<ResponseMapper<List<FavProductDto>>> getFavProduct(@CurrentUser User current) {
        return favService.getFavProduct(current);
    }

}
