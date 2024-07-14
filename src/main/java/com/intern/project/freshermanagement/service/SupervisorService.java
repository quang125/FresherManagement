package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Supervisor;
import com.intern.project.freshermanagement.data.request.CreateStaffRequest;

import java.util.List;

public interface SupervisorService {
    List<Supervisor> findAll();
    List<Supervisor> findAllActiveSupervisor();
    Supervisor findById(Long id);
    void delete(Long id);
}
