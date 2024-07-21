package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFresherRequest {
    private String resumeLink;
    private String fresherClass;
    private String studentCode;
}
