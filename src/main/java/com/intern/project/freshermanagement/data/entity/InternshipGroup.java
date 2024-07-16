package com.intern.project.freshermanagement.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JoinColumn(name = "user_id")
    private User supervisor;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "group_chat_id")
    private GroupChat groupChat;

}
