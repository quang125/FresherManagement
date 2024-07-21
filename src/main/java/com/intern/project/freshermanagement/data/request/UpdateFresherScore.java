package com.intern.project.freshermanagement.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFresherScore {
    private Double score1;
    private Double score2;
    private Double score3;
}
