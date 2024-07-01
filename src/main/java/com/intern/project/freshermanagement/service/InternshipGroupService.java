package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InternshipGroupService {
    List<InternshipGroup> findAll();
    List<InternshipGroup> findAllActiveGroups();
    InternshipGroup findById(Long id);
    InternshipGroup createGroup(MultipartFile file);
}
