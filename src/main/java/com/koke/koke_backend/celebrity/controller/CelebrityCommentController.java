package com.koke.koke_backend.celebrity.controller;

import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import com.koke.koke_backend.celebrity.service.CelebrityCommentService;
import com.koke.koke_backend.common.config.Uris;
import com.koke.koke_backend.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "셀럽 추천사 관리", description = "셀럽 추천사 관리 API")
@RestController
@RequiredArgsConstructor
public class CelebrityCommentController {

    private final CelebrityCommentService celebrityCommentService;

    @Tag(name = "셀럽 추천사 관리", description = "셀럽 추천사 관리 API")
    @Operation(summary = "셀럽 추천사 리스트 조회", description = "셀럽 추천사 리스트 조회 API")
    @GetMapping(value = Uris.CELEBRITY_ROOT)
    public ResponseEntity<ApiResponse<List<CelebrityComment>>> list() {
        return celebrityCommentService.list();
    }

}
