package com.intern.project.freshermanagement.data.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandMentorDTO {
    private String email;

    @JsonIgnore
    private String password;

    private String username;
    private String phoneNumber;
    private String fullName;
    private String profileImageUrl;
    private LocalDate dateOfBirth;
    private Long programmingLanguageId;
}
