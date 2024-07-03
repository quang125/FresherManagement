package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherRepository extends JpaRepository<Fresher,Long> {
    @Query("SELECT m FROM Fresher m WHERE m.internshipGroup.id=:groupId")
    List<Fresher> findByInternshipGroup(@Param("groupId") Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.internshipProject.projectName=:projectName and m.internshipGroup.id=:groupId")
    List<Fresher> findByInternshipProject(@Param("projectName") String projectName, @Param("groupId")  Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.internshipProject.programmingLanguage.languageName=:languageName " +
            "and m.internshipGroup.id=:groupId")
    List<Fresher> findByProgrammingLanguage(@Param("languageName") String languageName, @Param("groupId")  Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.internshipGroup.office.id=:officeId and m.internshipGroup.id=:groupId")
    List<Fresher> findByOffice(@Param("officeId") Long officeId, @Param("groupId")  Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.user.fullName=:name and m.internshipGroup.id=:groupId")
    List<Fresher> findByName(@Param("name") String name,  @Param("groupId")  Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.user.email=:email and m.internshipGroup.id=:groupId")
    List<Fresher> findByEmail(@Param("email") String email, @Param("groupId")  Long groupId);

    @Query("SELECT m FROM Fresher m WHERE m.internshipProject.projectName=:projectName")
    List<Fresher> findByInternshipProject(@Param("projectName") String projectName);

    @Query("SELECT m FROM Fresher m WHERE m.internshipProject.programmingLanguage.languageName=:languageName " )
    List<Fresher> findByProgrammingLanguage(@Param("languageName") String languageName);

    @Query("SELECT m FROM Fresher m WHERE m.internshipGroup.office.id=:officeId")
    List<Fresher> findByOffice(@Param("officeId") Long officeId);

    @Query("SELECT m FROM Fresher m WHERE m.user.fullName=:name")
    List<Fresher> findByName(@Param("name") String name);

    @Query("SELECT m FROM Fresher m WHERE m.user.email=:email")
    List<Fresher> findByEmail(@Param("email")String email);
}
