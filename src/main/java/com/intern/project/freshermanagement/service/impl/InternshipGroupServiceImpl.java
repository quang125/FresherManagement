package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.constants.GroupChatType;
import com.intern.project.freshermanagement.common.exception.InternshipGroupNotFoundException;
import com.intern.project.freshermanagement.common.log.Logger;
import com.intern.project.freshermanagement.data.entity.*;
import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import com.intern.project.freshermanagement.repository.InternshipGroupRepository;
import com.intern.project.freshermanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipGroupServiceImpl implements InternshipGroupService {
    private final InternshipGroupRepository internshipGroupRepository;
    private final OfficeService officeService;
    private final SupervisorService supervisorService;
    private final SchoolService schoolService;
    private final GroupChatService groupChatService;

    @Override
    public List<InternshipGroup> findAll() {
        return internshipGroupRepository.findAll();
    }

    @Override
    public List<InternshipGroup> findAllActiveGroups() {
        return internshipGroupRepository.findAllActiveInternshipGroups();
    }

    @Override
    public List<InternshipGroup> findByOffice(Long officeId) {
        return internshipGroupRepository.findByOffice(officeId);
    }

    @Override
    public InternshipGroup findById(Long id) {
        return internshipGroupRepository.findById(id)
                .orElseThrow(
                        () -> new InternshipGroupNotFoundException()
                );
    }

    @Override
    public InternshipGroup createGroup(MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public InternshipGroup createGroup(CreateInternshipGroupRequest request) {
        Office office=officeService.findById(request.getOfficeId());
        School school=schoolService.findById(request.getSchoolId());
        Supervisor supervisor=supervisorService.findById(request.getSupervisorId());
        GroupChat groupChat=groupChatService.createGroup("VMO_fresher_bot",request.getGroupChatName(), GroupChatType.ACTIVITY);
        InternshipGroup internshipGroup=InternshipGroup.builder()
                .office(office)
                .groupChat(groupChat)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .teacherName(request.getTeacherName())
                .teacherEmail(request.getTeacherEmail())
                .office(office)
                .school(school)
                .supervisor(supervisor)
                .build();
        return internshipGroupRepository.save(internshipGroup);
    }
}
