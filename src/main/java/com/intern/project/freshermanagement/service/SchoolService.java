package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.School;
import com.intern.project.freshermanagement.data.request.CommandSchoolDTO;

import java.util.List;

public interface SchoolService {
    School create(CommandSchoolDTO school);
    School update(CommandSchoolDTO school);
    void deleteSchool(Long id);
    List<School> findById(Long id);
    List<School> findByName(String schoolName);
    List<School> findAll();
}