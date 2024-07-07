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
    @Query("SELECT m FROM InternshipProject m WHERE m.status = true")
    List<InternshipProject> findAllActiveInternshipProjects();

    @Query("SELECT m FROM InternshipProject m WHERE m.status = true and m.projectName=: project")
    List<InternshipProject> findByProjectName(@Param("project") String projectName);

    @Query("SELECT m FROM InternshipProject m WHERE m.status = true and m.id=:id")
    Optional<InternshipProject> findById(@Param("id") Long id);

    @Query("SELECT m FROM InternshipProject m WHERE m.status = true and m.programmingLanguage.languageName=:languageName")
    List<InternshipProject> findByLanguageName(@Param("languageName") String languageName);

}
