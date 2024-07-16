package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.OfficeDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<OfficeDirector,Long> {
}
