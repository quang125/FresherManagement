package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    @Query("SELECT m FROM School m WHERE m.schoolName LIKE %:name% or m.acronym like %:name% and m.status=true")
    List<School> findBySchoolNameAndAcronym(@Param("name") String text);
    List<School> findAllByStatus(boolean status);
    Optional<School> findByIdAndStatus(Long id, boolean status);

}
