package com.intern.project.freshermanagement.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeFresherStats {
    private Long officeId;
    private String officeName;
    private Long fresherCount;
}
