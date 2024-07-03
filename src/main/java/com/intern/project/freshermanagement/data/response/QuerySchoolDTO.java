package com.intern.project.freshermanagement.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuerySchoolDTO {
    private String schoolName;
    private String acronym;
    private String location;
}
