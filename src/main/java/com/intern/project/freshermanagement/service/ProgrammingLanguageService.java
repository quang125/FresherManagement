package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;

import java.util.List;

public interface ProgrammingLanguageService {
    List<ProgrammingLanguage> findAll();
    List<ProgrammingLanguage> findAllActiveProgrammingLanguages();
    ProgrammingLanguage findById(Long id);
    ProgrammingLanguage create(ProgrammingLanguage programmingLanguage);
    void delete(Long id);
    ProgrammingLanguage update(ProgrammingLanguage programmingLanguage);
}
