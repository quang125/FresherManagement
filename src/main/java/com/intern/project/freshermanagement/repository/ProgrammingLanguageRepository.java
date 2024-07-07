package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Long> {
    @Query("SELECT m FROM ProgrammingLanguage m WHERE m.status = true")
    List<ProgrammingLanguage> findAllActiveProgramLanguages();
    @Query("SELECT m FROM ProgrammingLanguage m WHERE m.status = true and m.id=:id")
    Optional<ProgrammingLanguage> findById(@Param("id") Long id);
    @Query("SELECT m FROM ProgrammingLanguage m WHERE m.status = true and m.languageName=:language")
    List<ProgrammingLanguage> findByLanguageName(@Param("language") String languageName);
}
