package com.rewardCustomer.dto.rewards;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardsResponseDATA {

    private int rewardPointsPerMonth;
    private LocalDate  creationDate;
    
}
