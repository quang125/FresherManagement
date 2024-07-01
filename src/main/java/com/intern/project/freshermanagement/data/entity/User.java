package com.intern.project.freshermanagement.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String username;
    private String phoneNumber;

    private boolean status;
    private String fullName;
    private String profileImageUrl;
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

}
