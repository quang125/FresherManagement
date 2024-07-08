package com.intern.project.freshermanagement.data.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateFresherDTO {
    private String email;
    private String fullName;
    private LocalDate dateOfBirth;
    private String resumeLink;
    private String phoneNumber;
    private String fresherClass;
    private String studentCode;
    private Long internshipGroupId;
}
