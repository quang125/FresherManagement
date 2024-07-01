package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherRepository extends JpaRepository<Fresher,Long> {
}
