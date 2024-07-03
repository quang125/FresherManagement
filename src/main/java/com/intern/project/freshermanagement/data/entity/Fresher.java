package com.intern.project.freshermanagement.data.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fresher_score_id")
    private FresherScore fresherScore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "internship_group_id")
    private InternshipGroup internshipGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "internship_registration_content_id")
    private InternshipProject internshipProject;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}
