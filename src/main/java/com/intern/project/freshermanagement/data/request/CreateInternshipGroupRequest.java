package com.intern.project.freshermanagement.data.request;

import com.intern.project.freshermanagement.data.entity.Office;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInternshipGroupRequest {
    private String teacherName;
    private String teacherEmail;
    private long officeId;
    private long supervisorId;
    private long schoolId;
    private String groupChatName;
    private LocalDate startDate;
    private LocalDate endDate;
}
