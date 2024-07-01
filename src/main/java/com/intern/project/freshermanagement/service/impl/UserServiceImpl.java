package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.data.entity.User;
import com.intern.project.freshermanagement.repository.UserRepository;
import com.intern.project.freshermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
