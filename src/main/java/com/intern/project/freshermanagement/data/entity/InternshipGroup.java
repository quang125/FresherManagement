package com.intern.project.freshermanagement.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class InternshipGroup extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    private String teacherName;
    private String teacherEmail;
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Office office;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

}
