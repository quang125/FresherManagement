package com.intern.project.freshermanagement.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Office extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String location;
    private String officeName;
    private boolean status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_director_id")
    private OfficeDirector officeDirector;

}
