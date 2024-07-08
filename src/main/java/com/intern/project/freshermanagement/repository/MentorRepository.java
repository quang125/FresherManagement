package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    @Query("SELECT m FROM Mentor m WHERE m.user.isActive = true")
    List<Mentor> findAllActiveMentors();
    @Query("SELECT m FROM Mentor m WHERE m.user.isActive = true and m.id= :id" )
    Optional<Mentor> findById(@Param("id") Long id);
    @Query("SELECT m FROM Mentor m WHERE m.user.isActive = true AND m.programmingLanguage.languageName = :languageName")
    List<Mentor> findByProgrammingLanguage(@Param("languageName") String languageName);

}
