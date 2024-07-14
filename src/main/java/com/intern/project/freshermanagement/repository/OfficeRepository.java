package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office,Long> {
    List<Office> findByStatus(Boolean status);
    List<Office> findByName(String name);

}
