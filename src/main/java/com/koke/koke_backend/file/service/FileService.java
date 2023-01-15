package com.koke.koke_backend.file.service;

import com.koke.koke_backend.file.repository.FileInfoRepository;
import com.koke.koke_backend.file.repository.FileMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileMstRepository fileMstRepository;
    private final FileInfoRepository fileInfoRepository;

}
