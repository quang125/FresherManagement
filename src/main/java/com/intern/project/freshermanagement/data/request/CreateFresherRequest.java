package com.intern.project.freshermanagement.data.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class CreateFresherRequest {
    private String email;
    private String fullName;
    private LocalDate dateOfBirth;
    private String resumeLink;
    private String phoneNumber;
    private String fresherClass;
    private String studentCode;
}
