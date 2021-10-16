package com.rewardCustomer.dto.rewards;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardsResponseDTO {

    private String customerID;
    private int rewardPoints;
    private Date creationDate;
    
}
