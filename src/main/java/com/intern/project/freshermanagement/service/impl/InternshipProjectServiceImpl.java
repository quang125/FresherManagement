package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.InternshipProjectNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipProject;
import com.intern.project.freshermanagement.data.request.CommandProjectDTO;
import com.intern.project.freshermanagement.repository.InternshipProjectRepository;
import com.intern.project.freshermanagement.service.InternshipProjectService;
import com.intern.project.freshermanagement.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipProjectServiceImpl implements InternshipProjectService {
    private final InternshipProjectRepository internshipProjectRepository;
    private final ProgrammingLanguageService programmingLanguageService;
    @Override
    public List<InternshipProject> findAll() {
        return internshipProjectRepository.findAllActiveInternshipProjects();
    }

    @Override
    public InternshipProject findById(Long id) {
        return internshipProjectRepository.findById(id)
                .orElseThrow(()->new InternshipProjectNotFoundException());
    }

    @Override
    public InternshipProject create(CommandProjectDTO project) {
        InternshipProject internshipProject=new InternshipProject();
        internshipProject.setProjectName(project.getProjectName());
        internshipProject.setProjectDescriptionUrl(project.getProjectDescriptionUrl());
        internshipProject.setProgrammingLanguage(programmingLanguageService.findById(project.getLanguageId()));
        internshipProject.setStatus(true);
        return internshipProjectRepository.save(internshipProject);
    }

    @Override
    public void delete(Long id) {
        InternshipProject internshipProject=internshipProjectRepository.findById(id)
                .orElseThrow(()->new InternshipProjectNotFoundException());
        internshipProject.setStatus(false);
        internshipProjectRepository.save(internshipProject);
    }

    @Override
    public InternshipProject update(CommandProjectDTO project) {
        InternshipProject internshipProject=new InternshipProject();
        internshipProject.setProjectName(project.getProjectName());
        internshipProject.setProjectDescriptionUrl(project.getProjectDescriptionUrl());
        internshipProject.setProgrammingLanguage(programmingLanguageService.findById(project.getLanguageId()));
        return internshipProjectRepository.save(internshipProject);
    }

    @Override
    public List<InternshipProject> findByName(String name) {
        return internshipProjectRepository.findByProjectName(name);
    }

    @Override
    public List<InternshipProject> findByLanguageName(String languageName) {
        return internshipProjectRepository.findByLanguageName(languageName);
    }
}
