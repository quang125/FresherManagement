package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import com.intern.project.freshermanagement.data.request.CommandLanguageDTO;

import java.util.List;

public interface ProgrammingLanguageService {
    List<ProgrammingLanguage> findAll();
    List<ProgrammingLanguage> findAllActiveProgrammingLanguages();
    ProgrammingLanguage findById(Long id);
    ProgrammingLanguage create(CommandLanguageDTO programmingLanguage);
    List<ProgrammingLanguage> findByName(String languageName);
    void delete(Long id);
    ProgrammingLanguage update(CommandLanguageDTO programmingLanguage, Long languageId);
}
