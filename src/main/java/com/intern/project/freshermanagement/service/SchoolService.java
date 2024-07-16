package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.School;
import com.intern.project.freshermanagement.data.request.SchoolDTO;

import java.util.List;

public interface SchoolService {
    School create(SchoolDTO school);
    School update(SchoolDTO school, Long schoolId);
    void deleteSchool(Long id);
    School findById(Long id);
    School findById(Long id, boolean status);
    List<School> findByName(String schoolName);
    List<School> findByName(String schoolName, boolean status);
    List<School> findAll();
    List<School> findAll(boolean status);
}
