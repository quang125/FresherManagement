package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipGroupRepository extends JpaRepository<InternshipGroup,Long> {
    List<InternshipGroup> findAllByStatus(boolean status);
    InternshipGroup findByIdAndStatus(Long id, boolean status);
    List<InternshipGroup> findByOffice_Id(Long officeId);
    List<InternshipGroup> findByOffice_IdAndStatus(Long officeId, boolean status);
    List<InternshipGroup> findBySchool_Id(Long schoolId);
    List<InternshipGroup> findBySchool_IdAndStatus(Long schoolId, boolean status);

}
