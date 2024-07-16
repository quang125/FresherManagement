package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.SchoolNotFoundException;
import com.intern.project.freshermanagement.common.mapper.SchoolMapper;
import com.intern.project.freshermanagement.data.entity.School;
import com.intern.project.freshermanagement.data.request.SchoolDTO;
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
    public School create(SchoolDTO schoolDTO) {
        return schoolRepository.save(SchoolMapper.fromDTO(schoolDTO));
    }

    @Override
    public School update(SchoolDTO schoolDTO, Long schoolId) {
        School school=schoolRepository.findById(schoolId).orElseThrow(
                SchoolNotFoundException::new
        );
        School newSchool=SchoolMapper.fromDTO(schoolDTO);
        newSchool.setId(school.getId());
        return schoolRepository.save(newSchool);
    }

    @Override
    public void deleteSchool(Long id) {
        School school=schoolRepository.findById(id).orElseThrow(
                SchoolNotFoundException::new
        );
        school.setStatus(false);
        schoolRepository.save(school);
    }

    @Override
    public School findById(Long id) {
        return schoolRepository.findById(id).orElseThrow(SchoolNotFoundException::new);
    }

    @Override
    public School findById(Long id, boolean status) {
        return schoolRepository.findByIdAndStatus(id, status).orElseThrow(SchoolNotFoundException::new);
    }

    @Override
    public List<School> findByName(String schoolName) {
        return schoolRepository.findBySchoolNameAndAcronym(schoolName);
    }

    @Override
    public List<School> findByName(String schoolName, boolean status) {
        return null;
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public List<School> findAll(boolean status) {
        return  schoolRepository.findAllByStatus(status);
    }
}
