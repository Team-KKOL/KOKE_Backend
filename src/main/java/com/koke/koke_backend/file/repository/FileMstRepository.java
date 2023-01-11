package com.koke.koke_backend.file.repository;

import com.koke.koke_backend.file.entity.FileMst;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMstRepository extends JpaRepository<FileMst, Long>, QFileMstRepository {
}
