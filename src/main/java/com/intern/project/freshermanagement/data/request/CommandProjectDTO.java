package com.intern.project.freshermanagement.data.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandProjectDTO {
    private String projectName;

    private Long languageId;

    private String projectDescriptionUrl;
}
