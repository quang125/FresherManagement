package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.OfficeDirector;

import java.util.List;

public interface DirectorService {
    OfficeDirector findById(Long id);
    List<OfficeDirector> findAll();
    List<OfficeDirector> findAllActive();
    List<OfficeDirector> findByName(String name);
}
