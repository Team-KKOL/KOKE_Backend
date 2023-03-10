package com.koke.koke_backend.fav.controller;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.fav.dto.FavSaveDto;
import com.koke.koke_backend.fav.service.FavService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fav")
public class FavController {

    private final FavService favService;

    @PutMapping
    public ApiResponse<Object> fav(@RequestBody FavSaveDto dto) {
        return null;
    }

}
