package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Office;
import com.intern.project.freshermanagement.data.request.CreateOfficeRequest;
import com.intern.project.freshermanagement.data.request.UpdateOfficeRequest;

import java.util.List;

public interface OfficeService {
    List<Office> findAll();
    List<Office> findAll(boolean status);
    List<Office> findByName(String name);
    List<Office> findByName(String name, boolean status);
    Office findById(Long id);
    Office findById(Long id, boolean status);
    Office create(CreateOfficeRequest createOfficeRequest);
    Office update(UpdateOfficeRequest updateOfficeRequest, Long officeId);
    Office merge(Long primaryOfficeId, Long secondaryOfficeId, Long newDirectorId);
}
