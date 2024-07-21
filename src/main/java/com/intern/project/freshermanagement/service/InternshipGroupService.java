package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.request.CreateInternshipGroupRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InternshipGroupService {
    Page<InternshipGroup> findAll(Pageable pageable);
    Page<InternshipGroup> findAll(boolean status, Pageable pageable);
    List<InternshipGroup>findByOffice(Long officeId);
    Page<InternshipGroup>findByOffice(Long officeId, boolean status, Pageable pageable);
    InternshipGroup findById(Long id);
    InternshipGroup findById(Long id, boolean status);
    Page<InternshipGroup>findBySchool(Long schoolId, Pageable pageable);
    Page<InternshipGroup>findBySchool(Long schoolId, boolean status, Pageable pageable);
    InternshipGroup createGroup(CreateInternshipGroupRequest request, Long officeId);
}
