package com.intern.project.freshermanagement.controller;

import com.intern.project.freshermanagement.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mentor")
public class MentorController {
    private final MentorService mentorService;
}
