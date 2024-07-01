package com.intern.project.freshermanagement.model.entity;

import jakarta.persistence.*;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String schoolName;
    private String location;

}
