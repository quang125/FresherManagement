package com.intern.project.freshermanagement.common.mapper;

import com.intern.project.freshermanagement.data.entity.Mentor;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CreateStaffRequest;
import com.intern.project.freshermanagement.data.request.UpdateMentorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MentorMapper {
    public static Mentor fromDTO(CreateStaffRequest createStaffDTO){
        User user= User.builder().dateOfBirth(createStaffDTO.getDateOfBirth())
                .email(createStaffDTO.getEmail())
                .fullName(createStaffDTO.getFullName())
                .phoneNumber(createStaffDTO.getPhoneNumber())
                .isActive(true)
                .build();
        return Mentor.builder()
                .user(user)
                .build();
    }
    public static Mentor fromDTO(UpdateMentorDTO updateMentorDTO, Mentor mentor){
        return null;
    }
}
