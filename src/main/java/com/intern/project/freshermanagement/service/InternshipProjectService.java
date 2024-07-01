package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipProject;

import java.util.List;

public interface InternshipProjectService {
    List<InternshipProject> findAll();
    InternshipProject findById(Long id);
    InternshipProject create(InternshipProject internshipProject);
    void delete(Long id);
    InternshipProject update(InternshipProject internshipProject);
}
