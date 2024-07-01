package com.intern.project.freshermanagement.model.entity;

import jakarta.persistence.*;

@Entity
public class OfficeDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}
