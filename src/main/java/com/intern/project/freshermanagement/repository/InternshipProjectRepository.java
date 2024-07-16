package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.InternshipProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternshipProjectRepository extends JpaRepository<InternshipProject,Long> {
    List<InternshipProject> findAllByStatus(boolean status);

    @Query("SELECT p FROM InternshipProject p WHERE p.projectName LIKE %:projectName%")
    List<InternshipProject> findByProjectName(@Param("projectName") String projectName);


    Optional<InternshipProject> findByIdAndStatus(Long id, Boolean status);

    List<InternshipProject> findByProgrammingLanguage_Id(Long programmingLanguageId);

}
