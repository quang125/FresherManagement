package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Long> {
    Page<ProgrammingLanguage> findAllByStatus(boolean status, Pageable pageable);
    Optional<ProgrammingLanguage> findById(Long id);
    Optional<ProgrammingLanguage> findByIdAndStatus(Long id, boolean status);
    Page<ProgrammingLanguage> findByLanguageName(String languageName, Pageable pageable);
    Page<ProgrammingLanguage> findByLanguageNameAndStatus(String languageName,boolean status, Pageable pageable);
}
