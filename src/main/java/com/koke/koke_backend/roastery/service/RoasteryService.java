package com.koke.koke_backend.roastery.service;

import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoasteryService {

    private final RoasteryRepository roasteryRepository;

}
