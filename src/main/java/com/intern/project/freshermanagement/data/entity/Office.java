package com.intern.project.freshermanagement.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Office extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String location;
    private String officeName;
    private String status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_director_id")
    private OfficeDirector officeDirector;

}
