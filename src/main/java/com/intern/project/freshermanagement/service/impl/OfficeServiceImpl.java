package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.OfficeNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.entity.Office;
import com.intern.project.freshermanagement.data.entity.OfficeDirector;
import com.intern.project.freshermanagement.data.request.CreateOfficeRequest;
import com.intern.project.freshermanagement.data.request.UpdateOfficeRequest;
import com.intern.project.freshermanagement.repository.OfficeRepository;
import com.intern.project.freshermanagement.service.DirectorService;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import com.intern.project.freshermanagement.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;
    private final DirectorService directorService;
    private final InternshipGroupService internshipGroupService;

    @Override
    public List<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public List<Office> findAllActiveOffice() {
        return officeRepository.findByStatus(true);
    }

    @Override
    public List<Office> findByName(String name) {
        return null;
    }

    @Override
    public Office findById(Long id) {
        return officeRepository.findById(id).orElseThrow(
                ()->new OfficeNotFoundException()
        );
    }

    @Override
    public Office create(CreateOfficeRequest createOfficeRequest) {
        Office office=Office.builder()
                .officeName(createOfficeRequest.getOfficeName())
                .location(createOfficeRequest.getLocation())
                .officeDirector(directorService.findById(createOfficeRequest.getDirectorId()))
                .build();
        return officeRepository.save(office);
    }

    @Override
    public Office update(UpdateOfficeRequest updateOfficeRequest, Long officeId) {
        Office office=officeRepository.findById(officeId).orElseThrow(
                ()->new OfficeNotFoundException()
        );
        office.setOfficeName(updateOfficeRequest.getOfficeName());
        office.setLocation(updateOfficeRequest.getLocation());
        return officeRepository.save(office);
    }

    @Override
    public Office merge(Long primaryOfficeId, Long secondaryOfficeId, Long newDirectorId) {
        List<InternshipGroup>internshipGroups=internshipGroupService.findByOffice(secondaryOfficeId);
        Office primaryOffice=officeRepository.findById(primaryOfficeId).orElseThrow(
                ()->new OfficeNotFoundException()
        );
        Office secondaryOffice=officeRepository.findById(secondaryOfficeId).orElseThrow(
                ()->new OfficeNotFoundException()
        );
        secondaryOffice.setStatus(false);
        for(InternshipGroup internshipGroup:internshipGroups){
            internshipGroup.setOffice(primaryOffice);
        }
        OfficeDirector officeDirector=directorService.findById(newDirectorId);
        primaryOffice.setOfficeDirector(officeDirector);
        officeRepository.save(secondaryOffice);
        return officeRepository.save(primaryOffice);
    }


}
