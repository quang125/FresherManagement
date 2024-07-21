package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.constants.RoleConstant;
import com.intern.project.freshermanagement.common.exception.UserNotFoundException;
import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CreateFresherRequest;
import com.intern.project.freshermanagement.data.request.UpdateFresherRequest;
import com.intern.project.freshermanagement.data.request.UpdateFresherScore;
import com.intern.project.freshermanagement.repository.FresherRepository;
import com.intern.project.freshermanagement.service.FresherService;
import com.intern.project.freshermanagement.service.InternshipGroupService;
import com.intern.project.freshermanagement.service.RoleService;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {
    private final FresherRepository fresherRepository;
    private final InternshipGroupService internshipGroupService;
    private final RoleService roleService;
    private final UserService userService;

    @Override
    public Fresher createFresher(CreateFresherRequest createFresherDTO, Long groupId){
        Fresher fresher = createFresherFromRequest(createFresherDTO);
        InternshipGroup internshipGroup = internshipGroupService.findById(groupId, true);
        fresher.getUser().setRole(roleService.findByName(RoleConstant.FRESHER_ROLE));
        userService.create(fresher.getUser());
        fresher.setInternshipGroup(internshipGroup);
        fresherRepository.save(fresher);
        return fresher;
    }

    @Override
    public Fresher updateFresher(UpdateFresherRequest updateFresherDTO, Long id) {
        Fresher fresher=fresherRepository.findByIdAndUser_IsActive(id,true).orElseThrow(UserNotFoundException::new);
        fresher.setFresherClass(updateFresherDTO.getFresherClass());
        fresher.setResumeLink(updateFresherDTO.getResumeLink());
        fresher.setStudentCode(updateFresherDTO.getStudentCode());
        return fresherRepository.save(fresher);
    }

    @Override
    public void deleteFresher(Long id) {
        Fresher fresher=fresherRepository.findByIdAndUser_IsActive(id,true).orElseThrow(UserNotFoundException::new);
        fresher.getUser().setActive(false);
        fresherRepository.save(fresher);
    }

    @Override
    public Page<Fresher> findAll(Pageable pageable) {
        return fresherRepository.findAll(pageable);
    }
    @Override
    public Fresher findById(Long id) {
        return fresherRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public byte[] insertFreshersFromFile(MultipartFile file, Long groupId) {
        InternshipGroup internshipGroup = internshipGroupService.findById(groupId, true);
        try {
            List<Fresher> freshers = parseFreshersFromExcelFile(file.getInputStream());
            List<Fresher> existingFreshers = new ArrayList<>();
            for (Fresher fresher : freshers) {
                Optional<Fresher> existingFresher = fresherRepository.findByUser_EmailAndUser_IsActive(fresher.getUser().getEmail(),true);
                if (existingFresher.isPresent()) {
                    existingFreshers.add(existingFresher.get());
                } else {
                    fresher.setInternshipGroup(internshipGroup);
                    fresherRepository.save(fresher);
                }
            }
            if (!existingFreshers.isEmpty()) {
                return createErrorFile(existingFreshers);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file", e);
        }
        return null;
    }


    @Override
    public Page<Fresher> findByParams(Long groupId, Long languageId, String name, String email, Pageable pageable) {
        return fresherRepository.findByParams(groupId, languageId, name, email, pageable);
    }

    @Override
    public Page<Fresher> findByParams(Long groupId, Long languageId, String name, String email, boolean status, Pageable pageable) {
        return fresherRepository.findByParams(groupId, languageId, name, email, pageable);
    }

    @Override
    public void updateFresherScore(UpdateFresherScore updateFresherScore, Long id) {
        Fresher fresher=fresherRepository.findByIdAndUser_IsActive(id,true).orElseThrow(UserNotFoundException::new);
        fresher.setScore1(updateFresherScore.getScore1());
        fresher.setScore2(updateFresherScore.getScore2());
        fresher.setScore3(updateFresherScore.getScore3());
        fresherRepository.save(fresher);
    }

    private byte[] createErrorFile(List<Fresher> existingFreshers) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Existing Freshers");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Name");
        int rowNum = 1;
        for (Fresher fresher : existingFreshers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(fresher.getId());
            row.createCell(1).setCellValue(fresher.getUser().getEmail());
            row.createCell(2).setCellValue(fresher.getUser().getFullName());
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
    private List<Fresher> parseFreshersFromExcelFile(InputStream is) throws IOException {
        List<Fresher> freshers = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            CreateFresherRequest createFresherRequest= CreateFresherRequest.builder().email(row.getCell(0).getStringCellValue())
                    .fullName(row.getCell(1).getStringCellValue()).dateOfBirth(LocalDate.parse(row.getCell(2).getStringCellValue(), formatter))
                    .resumeLink(row.getCell(3).getStringCellValue()).phoneNumber(row.getCell(4).getStringCellValue())
                    .fresherClass(row.getCell(5).getStringCellValue()).studentCode(row.getCell(6).getStringCellValue()).build();
            Fresher fresher=createFresherFromRequest(createFresherRequest);
            freshers.add(fresher);
        }
        workbook.close();
        return freshers;
    }

    private Fresher createFresherFromRequest(CreateFresherRequest createFresherDTO){
        User user=new User();
        user.setEmail(createFresherDTO.getEmail());
        user.setFullName(createFresherDTO.getFullName());
        user.setPhoneNumber(createFresherDTO.getPhoneNumber());
        user.setDateOfBirth(createFresherDTO.getDateOfBirth());
        user.setActive(true);
        Fresher fresher=new Fresher();
        fresher.setUser(user);
        fresher.setFresherClass(createFresherDTO.getFresherClass());
        fresher.setResumeLink(createFresherDTO.getResumeLink());
        fresher.setStudentCode(createFresherDTO.getStudentCode());
        return fresher;
    }
}
