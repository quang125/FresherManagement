package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.data.entity.Supervisor;
import com.intern.project.freshermanagement.service.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {
    @Override
    public Supervisor findById(Long id) {
        return null;
    }
}
