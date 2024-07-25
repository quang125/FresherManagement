package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.response.FresherScoreStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FresherRepository extends JpaRepository<Fresher,Long> {
    Optional<Fresher>findByIdAndUser_IsActive(Long id, boolean status);
    Optional<Fresher> findByUser_EmailAndUser_IsActive(String email, boolean status);
    @Query("SELECT COUNT(f) FROM Fresher f WHERE f.internshipGroup.office.id = :officeId")
    long countFresherByOfficeId(@Param("officeId") Long officeId);
    @Query("SELECT new com.intern.project.freshermanagement.data.response.FresherScoreStats(f.averageScore, COUNT(f)) " +
            "FROM Fresher f GROUP BY f.averageScore")
    List<FresherScoreStats> countByAverageScoreGroup();
    @Query("SELECT f FROM Fresher f WHERE " +
            "(:groupId IS NULL OR f.internshipGroup.id = :groupId) AND " +
            "(:projectId IS NULL OR f.internshipProject.id = :projectId) AND " +
            "(:name IS NULL OR f.user.fullName LIKE %:name%) AND " +
            "(:email IS NULL OR f.user.email LIKE %:email%)")
    Page<Fresher> findByParams(@Param("groupId") Long groupId, @Param("projectId") Long projectId,
                               @Param("name") String name, @Param("email") String email, Pageable pageable);
}
