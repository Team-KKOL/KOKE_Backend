package com.koke.koke_backend.review.repository;

import com.koke.koke_backend.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, QReviewRepository {

}
