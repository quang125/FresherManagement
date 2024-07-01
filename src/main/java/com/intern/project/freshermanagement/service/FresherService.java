package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.data.entity.Fresher;
import com.intern.project.freshermanagement.data.request.CreateFresherDTO;

public interface FresherService {
    Fresher createFresher(CreateFresherDTO createFresherDTO);

}
