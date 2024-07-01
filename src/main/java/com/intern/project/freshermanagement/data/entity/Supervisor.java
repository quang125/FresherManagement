package com.intern.project.freshermanagement.data.entity;

import jakarta.persistence.*;

@Entity
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}
