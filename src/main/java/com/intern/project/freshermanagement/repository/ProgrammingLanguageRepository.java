package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Long> {
    @Query("SELECT m FROM ProgrammingLanguage m WHERE m.status = true")
    List<ProgrammingLanguage> findAllActiveProgramLanguages();
}
