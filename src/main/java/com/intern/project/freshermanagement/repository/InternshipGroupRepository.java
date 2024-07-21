package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipGroupRepository extends JpaRepository<InternshipGroup,Long> {
    InternshipGroup findByIdAndStatus(Long id, boolean status);
    Page<InternshipGroup> findByOffice_Id(Long officeId, Pageable pageable);
    List<InternshipGroup> findByOffice_Id(Long officeId);
    Page<InternshipGroup> findByOffice_IdAndStatus(Long officeId, boolean status, Pageable pageable);
    Page<InternshipGroup> findBySchool_Id(Long schoolId, Pageable pageable);
    Page<InternshipGroup> findBySchool_IdAndStatus(Long schoolId, boolean status, Pageable pageable);

}
