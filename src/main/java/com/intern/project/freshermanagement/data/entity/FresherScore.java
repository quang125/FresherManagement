package com.intern.project.freshermanagement.data.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class FresherScore extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private double score1;
    private double score2;
    private double score3;

    public double getAverageScore(){
        return (score1+score2+score3)/3;
    }

}
