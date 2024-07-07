package com.intern.project.freshermanagement.data.entity;

import javax.persistence.*;
import lombok.Data;

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
