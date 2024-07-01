package com.intern.project.freshermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String languageName;
    private String languageDescription;

    private boolean status;

}
