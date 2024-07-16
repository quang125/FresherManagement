package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.request.CreateFresherDTO;
import com.intern.project.freshermanagement.data.request.UpdateFresherDTO;

import java.util.List;

public interface FresherService {
    Fresher createFresher(CreateFresherDTO createFresherDTO);
    Fresher updateFresher(UpdateFresherDTO updateFresherDTO);
    void deleteFresher(Long id);
    List<Fresher> findAll();
    List<Fresher> findAll(boolean status);
    List<Fresher> findByProgrammingLanguage(Long languageId);
    List<Fresher> findByInternshipProject(Long projectId);
    List<Fresher> findByOffice(Long officeId);
    List<Fresher> findByName(String fresherName);
    List<Fresher> findByEmail(String fresherEmail);
    List<Fresher> findAll(Long groupId);
    List<Fresher> findByProgrammingLanguage(String languageName, Long groupId);
    List<Fresher> findByInternshipProject(String projectName, Long groupId);

    List<Fresher> findByOffice(Long officeId, Long groupId);

    List<Fresher> findByName(String fresherName, Long groupId);

    List<Fresher> findByEmail(String fresherEmail, Long groupId);
}
