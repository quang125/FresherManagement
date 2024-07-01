package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);
}
