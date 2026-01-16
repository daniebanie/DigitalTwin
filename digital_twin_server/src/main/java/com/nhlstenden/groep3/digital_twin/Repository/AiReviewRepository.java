package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Entity.AiReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiReviewRepository extends JpaRepository<AiReview, Integer> {
    List<AiReview> findAllByOrderByCreatedAtDesc();
}
