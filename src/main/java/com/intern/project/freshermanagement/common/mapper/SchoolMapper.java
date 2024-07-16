package com.intern.project.freshermanagement.common.mapper;

import com.intern.project.freshermanagement.data.entity.School;
import com.intern.project.freshermanagement.data.request.SchoolDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchoolMapper {
    public static School fromDTO(SchoolDTO commandSchoolDTO){
        return School.builder()
                .schoolName(commandSchoolDTO.getSchoolName())
                .acronym(commandSchoolDTO.getAcronym())
                .location(commandSchoolDTO.getLocation())
                .isActive(true)
                .build();
    }
}
