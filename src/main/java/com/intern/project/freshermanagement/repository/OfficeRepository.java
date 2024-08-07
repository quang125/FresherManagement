package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office,Long> {
    List<Office> findAllByStatus(Boolean status);
    List<Office> findByOfficeName(String name);
    List<Office> findByOfficeNameAndStatus(String name,boolean status);
    Optional<Office> findByIdAndStatus(Long id, boolean status);
}
