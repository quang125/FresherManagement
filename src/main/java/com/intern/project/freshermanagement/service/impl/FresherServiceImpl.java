package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.constants.RoleConstant;
import com.intern.project.freshermanagement.common.mapper.FresherMapper;
import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.request.CreateFresherDTO;
import com.intern.project.freshermanagement.repository.FresherRepository;
import com.intern.project.freshermanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        userService.saveUser(fresher.getUser());
        fresher.setInternshipGroup(internshipGroup);
        fresherRepository.save(fresher);
        mailService.sendActiveUserMail(createFresherDTO.getEmail());
        return fresher;
    }


}
