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
    @Query("SELECT m FROM School m WHERE m.schoolName LIKE %:name% or m.acronym like %:name% and m.isActive=true")
    List<School> findBySchoolNameAndAcronym(@Param("name") String text);
    @Query("SELECT m FROM School m WHERE m.isActive=true")
    List<School> findAllActiveSchools();
    @Query("SELECT m FROM School m WHERE m.isActive=true and m.id=:id")
    Optional<School> findById(@Param("id") Long id);

}
