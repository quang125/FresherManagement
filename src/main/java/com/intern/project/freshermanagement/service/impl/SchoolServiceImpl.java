package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.SchoolNotFoundException;
import com.intern.project.freshermanagement.common.mapper.SchoolMapper;
import com.intern.project.freshermanagement.data.entity.School;
import com.intern.project.freshermanagement.data.request.CommandSchoolDTO;
import com.intern.project.freshermanagement.repository.SchoolRepository;
import com.intern.project.freshermanagement.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    @Override
    public School create(CommandSchoolDTO schoolDTO) {
        return schoolRepository.save(SchoolMapper.fromDTO(schoolDTO));
    }

    @Override
    public School update(CommandSchoolDTO schoolDTO, Long schoolId) {
        School school=schoolRepository.findById(schoolId).orElseThrow(
                ()-> new SchoolNotFoundException()
        );
        School newSchool=SchoolMapper.fromDTO(schoolDTO);
        newSchool.setId(school.getId());
        return schoolRepository.save(newSchool);
    }

    @Override
    public void deleteSchool(Long id) {
        School school=schoolRepository.findById(id).orElseThrow(
                ()-> new SchoolNotFoundException()
        );
        school.setActive(false);
        schoolRepository.save(school);
    }

    @Override
    public List<School> findById(Long id) {
        return null;
    }

    @Override
    public List<School> findByName(String schoolName) {
        return schoolRepository.findBySchoolNameAndAcronym(schoolName);
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAllActiveSchools();
    }
}
