package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.constants.GroupChatType;
import com.intern.project.freshermanagement.common.exception.InternshipGroupNotFoundException;
import com.intern.project.freshermanagement.common.exception.OfficeNotFoundException;
import com.intern.project.freshermanagement.data.entity.*;
import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import com.intern.project.freshermanagement.repository.InternshipGroupRepository;
import com.intern.project.freshermanagement.repository.OfficeRepository;
import com.intern.project.freshermanagement.service.GroupChatService;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import com.intern.project.freshermanagement.service.SchoolService;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipGroupServiceImpl implements InternshipGroupService {
    private final InternshipGroupRepository internshipGroupRepository;
    private final OfficeRepository officeRepository;
    private final UserService userService;
    private final SchoolService schoolService;
    private final GroupChatService groupChatService;

    @Override
    public Page<InternshipGroup> findAll(Pageable pageable) {
        return internshipGroupRepository.findAll(pageable);
    }

    @Override
    public Page<InternshipGroup> findAll(boolean status, Pageable pageable) {
        return null;
    }

    @Override
    public List<InternshipGroup> findByOffice(Long officeId) {
        return internshipGroupRepository.findByOffice_Id(officeId);
    }

    @Override
    public Page<InternshipGroup> findByOffice(Long officeId, boolean status, Pageable pageable) {
        return internshipGroupRepository.findByOffice_IdAndStatus(officeId, status, pageable);
    }

    @Override
    public InternshipGroup findById(Long id) {
        return internshipGroupRepository.findById(id)
                .orElseThrow(
                        InternshipGroupNotFoundException::new
                );
    }

    @Override
    public InternshipGroup findById(Long id, boolean status) {
        return internshipGroupRepository.findByIdAndStatus(id, status);
    }

    @Override
    public Page<InternshipGroup> findBySchool(Long schoolId, Pageable pageable) {
        return internshipGroupRepository.findBySchool_Id(schoolId, pageable);
    }

    @Override
    public Page<InternshipGroup> findBySchool(Long schoolId, boolean status, Pageable pageable) {
        return internshipGroupRepository.findBySchool_IdAndStatus(schoolId,status, pageable);
    }



    @Override
    public InternshipGroup createGroup(CreateInternshipGroupRequest request, Long officeId) {
        Office office=officeRepository.findById(officeId).orElseThrow(OfficeNotFoundException::new);
        School school=schoolService.findById(request.getSchoolId());
        User supervisor=userService.findById(request.getSupervisorId());
        GroupChat groupChat=groupChatService.createGroup("VMO_fresher_bot",request.getGroupChatName(), GroupChatType.ACTIVITY);
        InternshipGroup internshipGroup=InternshipGroup.builder().office(office).
                groupChat(groupChat).startDate(request.getStartDate()).endDate(request.getEndDate()).
                teacherName(request.getTeacherName()).teacherEmail(request.getTeacherEmail()).office(office).
                school(school).supervisor(supervisor).build();
        return internshipGroupRepository.save(internshipGroup);
    }
}
