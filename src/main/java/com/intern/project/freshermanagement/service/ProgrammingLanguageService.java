package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import com.intern.project.freshermanagement.data.request.LanguageDTO;

import java.util.List;

public interface ProgrammingLanguageService {
    List<ProgrammingLanguage> findAll();
    List<ProgrammingLanguage> findAll(boolean status);
    ProgrammingLanguage findById(Long id);
    ProgrammingLanguage findById(Long id, boolean status);
    ProgrammingLanguage create(LanguageDTO programmingLanguage);
    List<ProgrammingLanguage> findByName(String languageName);
    List<ProgrammingLanguage> findByName(String languageName, boolean status);
    void delete(Long id);
    ProgrammingLanguage update(LanguageDTO programmingLanguage, Long languageId);
}
