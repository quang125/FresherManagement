package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.data.entity.Supervisor;
import com.intern.project.freshermanagement.data.request.CreateStaffRequest;
import com.intern.project.freshermanagement.service.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {
    @Override
    public List<Supervisor> findAll() {
        return null;
    }

    @Override
    public List<Supervisor> findAllActiveSupervisor() {
        return null;
    }

    @Override
    public Supervisor findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
