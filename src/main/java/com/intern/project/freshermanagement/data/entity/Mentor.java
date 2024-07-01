package com.intern.project.freshermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mentor extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    private boolean status;

}
