package com.koke.koke_backend.file.repository;

import com.koke.koke_backend.file.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QFileInfoRepository {
}
