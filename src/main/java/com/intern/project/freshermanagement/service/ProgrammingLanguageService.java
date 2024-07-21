package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import com.intern.project.freshermanagement.data.request.LanguageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgrammingLanguageService {
    Page<ProgrammingLanguage> findAll(Pageable pageable);
    Page<ProgrammingLanguage> findAll(boolean status, Pageable pageable);
    ProgrammingLanguage findById(Long id);
    ProgrammingLanguage findById(Long id, boolean status);
    ProgrammingLanguage create(LanguageDTO programmingLanguage);
    Page<ProgrammingLanguage> findByName(String languageName, Pageable pageable);
    Page<ProgrammingLanguage> findByName(String languageName, boolean status, Pageable pageable);
    void delete(Long id);
    ProgrammingLanguage update(LanguageDTO programmingLanguage, Long languageId);
}
