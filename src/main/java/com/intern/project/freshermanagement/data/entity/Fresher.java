package com.intern.project.freshermanagement.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Fresher extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String resumeLink;
    private String fresherClass;
    private String studentCode;

    private double score1;
    private double score2;
    private double score3;
    private double averageScore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "internship_group_id")
    private InternshipGroup internshipGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "internship_registration_content_id")
    private InternshipProject internshipProject;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}
