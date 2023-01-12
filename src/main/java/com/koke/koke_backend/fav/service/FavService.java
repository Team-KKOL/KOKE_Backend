package com.koke.koke_backend.fav.service;

import com.koke.koke_backend.fav.repository.FavRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavService {

    private final FavRepository favRepository;

}
