package com.koke.koke_backend.celebrity.repository;

import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelebrityCommentRepository extends JpaRepository<CelebrityComment, Integer> {
}
