package com.intern.project.freshermanagement.common.mapper;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.entity.Mentor;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CommandMentorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MentorMapper {
    public static Mentor fromDTO(CommandMentorDTO commandMentorDTO){
        User user= User.builder().dateOfBirth(commandMentorDTO.getDateOfBirth())
                .email(commandMentorDTO.getEmail())
                .fullName(commandMentorDTO.getFullName())
                .phoneNumber(commandMentorDTO.getPhoneNumber())
                .status(true)
                .build();
        return Mentor.builder()
                .user(user)
                .build();
    }
}
