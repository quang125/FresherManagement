package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InternshipGroupService {
    List<InternshipGroup> findAll();
    List<InternshipGroup> findAll(boolean status);
    List<InternshipGroup>findByOffice(Long officeId);
    List<InternshipGroup>findByOffice(Long officeId, boolean status);
    InternshipGroup findById(Long id);
    InternshipGroup findById(Long id, boolean status);
    List<InternshipGroup>findBySchool(Long schoolId);
    List<InternshipGroup>findBySchool(Long schoolId, boolean status);
    InternshipGroup createGroup(MultipartFile file);
    InternshipGroup createGroup(CreateInternshipGroupRequest request, Long officeId);
}
