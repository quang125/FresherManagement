package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Mentor;
import com.intern.project.freshermanagement.data.request.CreateMentorDTO;
import com.intern.project.freshermanagement.data.request.UpdateMentorDTO;

import java.util.List;

public interface MentorService {
    List<Mentor> findAll();
    List<Mentor> findAllActiveMentors();
    Mentor findById(Long id);
    List<Mentor> findByProgrammingLanguage(String languageName);
    Mentor create(CreateMentorDTO mentor);
    void delete(Long id);
    Mentor update(UpdateMentorDTO mentor, Long mentorId);
}
