package com.intern.project.freshermanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateFresherDTO {
    private String email;
    private String password;

    private String username;

    private boolean status;
    private String fullName;
    private LocalDate dateOfBirth;
    private String resumeLink;
    private String phoneNumber;
    private String fresherClass;
    private String studentCode;
    private Long internshipGroupId;
}
