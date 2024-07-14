package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOfficeRequest {
    private String location;
    private String officeName;
    private Long directorId;
}
