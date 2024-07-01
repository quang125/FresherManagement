package com.intern.project.freshermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class InternshipProject extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean status;
    private String projectName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    private String projectDescriptionUrl;

}
