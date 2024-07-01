package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.FresherScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherScoreRepository extends JpaRepository<FresherScore,Long> {
}
