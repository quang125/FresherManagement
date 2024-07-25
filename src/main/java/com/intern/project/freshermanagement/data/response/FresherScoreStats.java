package com.intern.project.freshermanagement.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FresherScoreStats {
    private double score;
    private long fresherCount;
}
