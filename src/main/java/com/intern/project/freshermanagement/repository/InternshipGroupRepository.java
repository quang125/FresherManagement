package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.InternshipGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipGroupRepository extends JpaRepository<InternshipGroup,Long> {
    @Query("SELECT m FROM InternshipGroup m WHERE m.status = true")
    List<InternshipGroup> findAllActiveInternshipGroups();
    List<InternshipGroup> findByOffice(Long officeId);
}
