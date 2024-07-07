package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipProject;
import com.intern.project.freshermanagement.data.request.CommandProjectDTO;

import java.util.List;

public interface InternshipProjectService {
    List<InternshipProject> findAll();
    InternshipProject findById(Long id);
    InternshipProject create(CommandProjectDTO internshipProject);
    void delete(Long id);
    InternshipProject update(CommandProjectDTO internshipProject, Long projectId);
    List<InternshipProject> findByName(String name);
    List<InternshipProject> findByLanguageName(String languageName);
}
