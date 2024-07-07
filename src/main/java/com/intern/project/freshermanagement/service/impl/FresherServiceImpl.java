package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.constants.RoleConstant;
import com.intern.project.freshermanagement.common.exception.UserNotFoundException;
import com.intern.project.freshermanagement.common.mapper.FresherMapper;
import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.request.CreateFresherDTO;
import com.intern.project.freshermanagement.data.request.UpdateFresherDTO;
import com.intern.project.freshermanagement.repository.FresherRepository;
import com.intern.project.freshermanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {
    private final FresherRepository fresherRepository;
    private final InternshipGroupService internshipGroupService;
    private final RoleService roleService;
    private final MailService mailService;
    private final UserService userService;

    @Override
    public Fresher createFresher(CreateFresherDTO createFresherDTO){
        Fresher fresher = FresherMapper.toEntity(createFresherDTO);
        InternshipGroup internshipGroup = internshipGroupService.findById(createFresherDTO.getInternshipGroupId());
        fresher.getUser().setRole(roleService.findByName(RoleConstant.FRESHER_ROLE));
        userService.create(fresher.getUser());
        fresher.setInternshipGroup(internshipGroup);
        fresherRepository.save(fresher);
        mailService.sendActiveUserMail(createFresherDTO.getEmail());
        return fresher;
    }

    @Override
    public Fresher updateFresher(UpdateFresherDTO updateFresherDTO) {
        return null;
    }

    @Override
    public void deleteFresher(Long id) {
        Fresher fresher=fresherRepository.findById(id).orElseThrow(()->new UserNotFoundException());
        fresher.getUser().setStatus(false);
        fresherRepository.save(fresher);
    }

    @Override
    public List<Fresher> findAll() {
        return fresherRepository.findAll();
    }

    @Override
    public List<Fresher> findByProgrammingLanguage(String languageName) {
        return fresherRepository.findByProgrammingLanguage(languageName);
    }


    @Override
    public List<Fresher> findByInternshipProject(String projectName) {
        return fresherRepository.findByInternshipProject(projectName);
    }


    @Override
    public List<Fresher> findByOffice(Long officeId) {
        return fresherRepository.findByOffice(officeId);
    }

    @Override
    public List<Fresher> findByName(String fresherName) {
        return fresherRepository.findByName(fresherName);
    }

    @Override
    public List<Fresher> findByEmail(String fresherEmail) {
        return fresherRepository.findByEmail(fresherEmail);
    }

    @Override
    public List<Fresher> findAll(Long groupId) {
        return fresherRepository.findByInternshipGroup(groupId);
    }

    @Override
    public List<Fresher> findByProgrammingLanguage(String languageName, Long groupId) {
        return fresherRepository.findByProgrammingLanguage(languageName, groupId);
    }


    @Override
    public List<Fresher> findByInternshipProject(String projectName, Long groupId) {
        return fresherRepository.findByInternshipProject(projectName, groupId);
    }

    @Override
    public List<Fresher> findByOffice(Long officeId, Long groupId) {
        return fresherRepository.findByOffice(officeId, groupId);
    }

    @Override
    public List<Fresher> findByName(String fresherName, Long groupId) {
        return fresherRepository.findByName(fresherName, groupId);
    }

    @Override
    public List<Fresher> findByEmail(String fresherEmail, Long groupId) {
        return fresherRepository.findByEmail(fresherEmail, groupId);
    }
}
