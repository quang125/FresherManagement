package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.MentorNotFoundException;
import com.intern.project.freshermanagement.data.entity.Mentor;
import com.intern.project.freshermanagement.repository.MentorRepository;
import com.intern.project.freshermanagement.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;
    @Override
    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }

    @Override
    public List<Mentor> findAllActiveMentors() {
        return mentorRepository.findAllActiveMentors();
    }

    @Override
    public Mentor findById(Long id) {
        return mentorRepository.findById(id).orElseThrow(()->new MentorNotFoundException());
    }

    @Override
    public List<Mentor> findByProgrammingLanguage(String languageName) {
        return mentorRepository.findByProgrammingLanguage(languageName);
    }

    @Override
    public Mentor create(Mentor mentor) {
        mentor.setStatus(true);
        return mentorRepository.save(mentor);
    }

    @Override
    public void delete(Long id) {
        Mentor mentor=mentorRepository.findById(id)
                .orElseThrow(()->new MentorNotFoundException());
        mentor.setStatus(false);
        mentorRepository.save(mentor);
    }

    @Override
    public Mentor update(Mentor mentor) {
        return mentorRepository.save(mentor);
    }
}
