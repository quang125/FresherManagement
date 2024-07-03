package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandSchoolDTO {
    private String schoolName;
    private String acronym;
    private String location;
}
