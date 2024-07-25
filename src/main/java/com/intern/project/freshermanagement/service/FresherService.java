package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.request.CreateFresherRequest;
import com.intern.project.freshermanagement.data.request.UpdateFresherRequest;
import com.intern.project.freshermanagement.data.request.UpdateFresherScore;
import com.intern.project.freshermanagement.data.response.FresherScoreStats;
import com.intern.project.freshermanagement.data.response.OfficeFresherStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FresherService {
    Fresher createFresher(CreateFresherRequest createFresherDTO, Long groupId);
    Fresher updateFresher(UpdateFresherRequest updateFresherDTO, Long id);
    void deleteFresher(Long id);
    Page<Fresher> findAll(Pageable pageable);
    Fresher findById(Long id);
    byte[] insertFreshersFromFile(MultipartFile multipartFile, Long groupId);
    Page<Fresher> findByParams(Long groupId, Long languageId, String name, String email, Pageable pageable);
    Page<Fresher> findByParams(Long groupId, Long languageId, String name, String email, boolean status, Pageable pageable);

    void updateFresherScore(UpdateFresherScore updateFresherScore, Long id);
    List<OfficeFresherStats> getFresherStatsByOffice();
    List<FresherScoreStats> getFresherStatsByScore();

}
