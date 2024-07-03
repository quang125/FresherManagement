package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.InternshipProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipProjectRepository extends JpaRepository<InternshipProject,Long> {
    @Query("SELECT m FROM InternshipProject m WHERE m.status = true")
    List<InternshipProject> findAllActiveInternshipProjects();
    List<InternshipProject> findByProjectName(String projectName);
    @Query("SELECT m FROM InternshipProject m WHERE m.status = true and m.programmingLanguage.languageName=:languageName")
    List<InternshipProject> findByLanguageName(@Param("languageName") String languageName);

}
