package com.intern.project.freshermanagement.data.entity;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    protected Date createdDate;

    @LastModifiedDate
    protected Date lastModifiedDate;

    @CreatedBy
    protected String createdBy;

    @LastModifiedBy
    protected String lastModifiedBy;
}
