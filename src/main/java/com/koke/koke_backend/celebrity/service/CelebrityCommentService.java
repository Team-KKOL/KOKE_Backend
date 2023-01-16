package com.koke.koke_backend.celebrity.service;

import com.koke.koke_backend.celebrity.repository.CelebrityCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CelebrityCommentService {

    private final CelebrityCommentRepository celebrityCommentRepository;



}
