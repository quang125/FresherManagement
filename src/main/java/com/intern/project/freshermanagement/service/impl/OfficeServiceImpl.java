package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.OfficeNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.entity.Office;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CreateOfficeRequest;
import com.intern.project.freshermanagement.data.request.UpdateOfficeRequest;
import com.intern.project.freshermanagement.repository.OfficeRepository;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import com.intern.project.freshermanagement.service.OfficeService;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;
    private final UserService userService;
    private final InternshipGroupService internshipGroupService;

    @Override
    public List<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public List<Office> findAll(boolean status) {
        return officeRepository.findAllByStatus(status);
    }


    @Override
    public List<Office> findByName(String name) {
        return officeRepository.findByOfficeName(name);
    }

    @Override
    public List<Office> findByName(String name, boolean status) {
        return officeRepository.findByOfficeNameAndStatus(name,status);
    }

    @Override
    public Office findById(Long id) {
        return officeRepository.findById(id).orElseThrow(
                OfficeNotFoundException::new
        );
    }

    @Override
    public Office findById(Long id, boolean status) {
        return officeRepository.findByIdAndStatus(id,status).orElseThrow(
                OfficeNotFoundException::new
        );
    }

    @Override
    public Office create(CreateOfficeRequest createOfficeRequest) {
        Office office=Office.builder()
                .officeName(createOfficeRequest.getOfficeName())
                .location(createOfficeRequest.getLocation())
                .officeDirector(userService.findById(createOfficeRequest.getDirectorId()))
                .build();
        return officeRepository.save(office);
    }

    @Override
    public Office update(UpdateOfficeRequest updateOfficeRequest, Long officeId) {
        Office office=officeRepository.findById(officeId).orElseThrow(
                OfficeNotFoundException::new
        );
        office.setOfficeName(updateOfficeRequest.getOfficeName());
        office.setLocation(updateOfficeRequest.getLocation());
        return officeRepository.save(office);
    }

    @Override
    public Office merge(Long primaryOfficeId, Long secondaryOfficeId, Long newDirectorId) {
        Office primaryOffice=officeRepository.findById(primaryOfficeId).orElseThrow(
                OfficeNotFoundException::new
        );
        Office secondaryOffice=officeRepository.findById(secondaryOfficeId).orElseThrow(
                OfficeNotFoundException::new
        );
        List<InternshipGroup>internshipGroups=internshipGroupService.findByOffice(secondaryOfficeId);
        secondaryOffice.setStatus(false);
        for(InternshipGroup internshipGroup:internshipGroups){
            internshipGroup.setOffice(primaryOffice);
        }
        User officeDirector=userService.findById(newDirectorId);
        primaryOffice.setOfficeDirector(officeDirector);
        officeRepository.save(secondaryOffice);
        return officeRepository.save(primaryOffice);
    }


}
