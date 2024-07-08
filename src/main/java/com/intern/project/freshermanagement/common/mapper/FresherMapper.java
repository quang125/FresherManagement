package com.intern.project.freshermanagement.common.mapper;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.data.request.CreateFresherDTO;
import com.intern.project.freshermanagement.data.request.UpdateFresherDTO;
import com.intern.project.freshermanagement.data.response.FresherDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FresherMapper {
    public static FresherDTO toDTO(Fresher fresher){
        return new FresherDTO();
    }
    public static Fresher toEntity(CreateFresherDTO createFresherDTO){
        User user=new User();
        user.setEmail(createFresherDTO.getEmail());
        user.setFullName(createFresherDTO.getFullName());
        user.setPhoneNumber(createFresherDTO.getPhoneNumber());
        user.setDateOfBirth(createFresherDTO.getDateOfBirth());
        user.setActive(true);
        Fresher fresher=new Fresher();
        fresher.setUser(user);
        fresher.setFresherClass(createFresherDTO.getFresherClass());
        fresher.setResumeLink(createFresherDTO.getResumeLink());
        fresher.setStudentCode(createFresherDTO.getStudentCode());
        return fresher;
    }
    public static Fresher toEntity(UpdateFresherDTO updateFresherDTO){
        return new Fresher();
    }
}
