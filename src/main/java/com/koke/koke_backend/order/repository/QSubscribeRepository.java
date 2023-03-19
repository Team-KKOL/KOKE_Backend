package com.koke.koke_backend.order.repository;

import com.koke.koke_backend.order.dto.SubscribeListResponseDto;

import java.util.List;

public interface QSubscribeRepository {
    List<SubscribeListResponseDto> list(String userId);
}
