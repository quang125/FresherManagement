package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor,Long> {
    @Query("SELECT m FROM Mentor m WHERE m.user.status = true")
    List<Mentor> findAllActiveMentors();
    @Query("SELECT m FROM Mentor m WHERE m.user.status = true AND m.programmingLanguage.languageName = :languageName")
    List<Mentor> findByProgrammingLanguage(@Param("languageName") String languageName);

}
