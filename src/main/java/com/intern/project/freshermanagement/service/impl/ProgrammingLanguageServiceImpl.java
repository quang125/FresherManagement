package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.ProgrammingLanguageNotFoundException;
import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import com.intern.project.freshermanagement.data.request.CommandLanguageDTO;
import com.intern.project.freshermanagement.repository.ProgrammingLanguageRepository;
import com.intern.project.freshermanagement.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {
    private final ProgrammingLanguageRepository programmingLanguageRepository;
    @Override
    public List<ProgrammingLanguage> findAll() {
        return programmingLanguageRepository.findAll();
    }

    @Override
    public List<ProgrammingLanguage> findAllActiveProgrammingLanguages() {
        return programmingLanguageRepository.findAllActiveProgramLanguages();
    }

    @Override
    public ProgrammingLanguage findById(Long id) {
        return programmingLanguageRepository.findById(id)
                .orElseThrow(()->new ProgrammingLanguageNotFoundException());
    }

    @Override
    public ProgrammingLanguage create(CommandLanguageDTO programmingLanguage) {
        ProgrammingLanguage language=new ProgrammingLanguage();
        language.setLanguageName(programmingLanguage.getLanguageName());
        language.setLanguageDescription(programmingLanguage.getLanguageDescription());
        language.setStatus(true);
        return programmingLanguageRepository.save(language);
    }

    @Override
    public List<ProgrammingLanguage> findByName(String languageName) {
        return programmingLanguageRepository.findByLanguageName(languageName);
    }

    @Override
    public void delete(Long id) {
        ProgrammingLanguage programmingLanguage=programmingLanguageRepository.findById(id).orElseThrow(
                ()-> new ProgrammingLanguageNotFoundException()
        );
        programmingLanguage.setStatus(false);
        programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public ProgrammingLanguage update(CommandLanguageDTO programmingLanguage) {
        ProgrammingLanguage language=new ProgrammingLanguage();
        language.setLanguageName(programmingLanguage.getLanguageName());
        language.setLanguageDescription(programmingLanguage.getLanguageDescription());
        return programmingLanguageRepository.save(language);
    }
}
