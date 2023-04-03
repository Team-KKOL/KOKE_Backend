package com.koke.koke_backend.celebrity.service;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import com.koke.koke_backend.celebrity.repository.CelebrityCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CelebrityCommentService {

    private final CelebrityCommentRepository celebrityCommentRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseMapper<List<CelebrityComment>>> list() {
        List<CelebrityComment> findAll = celebrityCommentRepository.findAll();
        return ResponseMapper.success(findAll);
    }

}
