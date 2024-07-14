package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Office;
import com.intern.project.freshermanagement.data.request.CreateOfficeRequest;
import com.intern.project.freshermanagement.data.request.UpdateOfficeRequest;

import java.util.List;

public interface OfficeService {
    List<Office> findAll();
    List<Office> findAllActiveOffice();
    List<Office> findByName(String name);

    Office findById(Long id);

    Office create(CreateOfficeRequest createOfficeRequest);

    Office update(UpdateOfficeRequest updateOfficeRequest, Long officeId);

    Office merge(Long primaryOfficeId, Long secondaryOfficeId, Long newDirectorId);
    
}
