package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipProject;
import com.intern.project.freshermanagement.data.request.ProjectDTO;

import java.util.List;

public interface InternshipProjectService {
    List<InternshipProject> findAll();
    List<InternshipProject> findAll(boolean status);
    InternshipProject findById(Long id);
    InternshipProject findById(Long id, boolean status);
    InternshipProject create(ProjectDTO internshipProject);
    void delete(Long id);
    InternshipProject update(ProjectDTO internshipProject, Long projectId);
    List<InternshipProject> findByName(String name);
    List<InternshipProject> findByName(String name, boolean status);
    List<InternshipProject> findByLanguage(Long languageId);
}
