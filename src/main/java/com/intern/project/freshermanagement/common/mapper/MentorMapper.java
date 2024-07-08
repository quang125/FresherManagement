package com.intern.project.freshermanagement.common.mapper;

import com.intern.project.freshermanagement.data.entity.Mentor;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CreateMentorDTO;
import com.intern.project.freshermanagement.data.request.UpdateMentorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MentorMapper {
    public static Mentor fromDTO(CreateMentorDTO createMentorDTO){
        User user= User.builder().dateOfBirth(createMentorDTO.getDateOfBirth())
                .email(createMentorDTO.getEmail())
                .fullName(createMentorDTO.getFullName())
                .phoneNumber(createMentorDTO.getPhoneNumber())
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
