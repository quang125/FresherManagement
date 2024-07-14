package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffRequest {
    private String email;
    private String username;
    private String phoneNumber;
    private String fullName;
    private String profileImageUrl;
    private LocalDate dateOfBirth;
    private Long programmingLanguageId;
}
