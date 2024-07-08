package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT m FROM User m WHERE m.email=:email and m.isActive=:false")
    Optional<User> findInactiveUserByEmail(@Param("email") String email);
}
