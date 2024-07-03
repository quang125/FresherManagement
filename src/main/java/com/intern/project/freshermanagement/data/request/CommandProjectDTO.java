package com.intern.project.freshermanagement.data.request;

import com.intern.project.freshermanagement.data.entity.ProgrammingLanguage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
