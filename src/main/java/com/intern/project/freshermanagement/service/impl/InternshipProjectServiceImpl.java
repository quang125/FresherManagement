package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.InternshipProjectNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipProject;
import com.intern.project.freshermanagement.repository.InternshipProjectRepository;
import com.intern.project.freshermanagement.service.InternshipProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipProjectServiceImpl implements InternshipProjectService {
    private final InternshipProjectRepository internshipProjectRepository;
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
    public InternshipProject create(InternshipProject internshipProject) {
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
    public InternshipProject update(InternshipProject internshipProject) {
        return internshipProjectRepository.save(internshipProject);
    }
}
