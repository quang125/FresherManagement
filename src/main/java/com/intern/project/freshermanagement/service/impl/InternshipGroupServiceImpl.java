package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.InternshipGroupNotFoundException;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.repository.InternshipGroupRepository;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipGroupServiceImpl implements InternshipGroupService {
    private final InternshipGroupRepository internshipGroupRepository;

    @Override
    public List<InternshipGroup> findAll() {
        return internshipGroupRepository.findAll();
    }

    @Override
    public List<InternshipGroup> findAllActiveGroups() {
        return internshipGroupRepository.findAllActiveInternshipGroups();
    }

    @Override
    public InternshipGroup findById(Long id) {
        return internshipGroupRepository.findById(id)
                .orElseThrow(
                        () -> new InternshipGroupNotFoundException()
                );
    }

    @Override
    public InternshipGroup createGroup(MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
