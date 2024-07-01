package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.data.entity.Role;
import com.intern.project.freshermanagement.repository.RoleRepository;
import com.intern.project.freshermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
