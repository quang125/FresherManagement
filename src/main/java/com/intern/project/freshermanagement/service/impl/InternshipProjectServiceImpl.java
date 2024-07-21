package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.InternshipProjectNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipProject;
import com.intern.project.freshermanagement.data.request.ProjectDTO;
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
        return internshipProjectRepository.findAll();
    }
    @Override
    public List<InternshipProject> findAll(boolean status) {
        return internshipProjectRepository.findAllByStatus(status);
    }

    @Override
    public InternshipProject findById(Long id) {
        return internshipProjectRepository.findById(id)
                .orElseThrow(InternshipProjectNotFoundException::new);
    }

    @Override
    public InternshipProject findById(Long id, boolean status) {
        return internshipProjectRepository.findByIdAndStatus(id, status)
                .orElseThrow(InternshipProjectNotFoundException::new);
    }

    @Override
    public InternshipProject create(ProjectDTO project) {
        InternshipProject internshipProject=new InternshipProject();
        internshipProject.setProjectName(project.getProjectName());
        internshipProject.setProjectDescriptionUrl(project.getProjectDescriptionUrl());
        internshipProject.setProgrammingLanguage(programmingLanguageService.findById(project.getLanguageId()));
        internshipProject.setStatus(true);
        return internshipProjectRepository.save(internshipProject);
    }

    @Override
    public void delete(Long id) {
        InternshipProject internshipProject=internshipProjectRepository.findByIdAndStatus(id, true)
                .orElseThrow(InternshipProjectNotFoundException::new);
        internshipProject.setStatus(false);
        internshipProjectRepository.save(internshipProject);
    }

    @Override
    public InternshipProject update(ProjectDTO project, Long projectId) {
        InternshipProject internshipProject=internshipProjectRepository.findByIdAndStatus(projectId, true)
                .orElseThrow(InternshipProjectNotFoundException::new);
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
    public List<InternshipProject> findByName(String name, boolean status) {
        return internshipProjectRepository.findByProjectNameAndStatus(name, status);
    }

    @Override
    public List<InternshipProject> findByLanguage(Long languageId) {
        return internshipProjectRepository.findByProgrammingLanguage_Id(languageId);
    }

    @Override
    public List<InternshipProject> findByLanguage(Long languageId, boolean status) {
        return internshipProjectRepository.findByStatusAndProgrammingLanguage_Id(languageId,status);
    }

}
