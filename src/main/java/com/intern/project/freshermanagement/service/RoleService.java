package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String roleName);
    List<Role> findAll();
}
