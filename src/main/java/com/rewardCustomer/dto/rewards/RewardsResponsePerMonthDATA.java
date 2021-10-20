package com.rewardCustomer.dto.rewards;

import java.sql.Date;

import lombok.Data;

@Data
public class RewardsResponsePerMonthDATA {

    private int rewardPointsPerMonth;
    private Date creationDate;
    
}
