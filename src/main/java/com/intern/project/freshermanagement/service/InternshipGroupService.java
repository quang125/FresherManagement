package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InternshipGroupService {
    List<InternshipGroup> findAll();
    List<InternshipGroup> findAllActiveGroups();
    List<InternshipGroup>findByOffice(Long officeId);
    InternshipGroup findById(Long id);
    InternshipGroup createGroup(MultipartFile file);
    InternshipGroup createGroup(CreateInternshipGroupRequest request);
}
