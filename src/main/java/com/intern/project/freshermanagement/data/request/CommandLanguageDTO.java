package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandLanguageDTO {
    private String languageName;
    private String languageDescription;
}
