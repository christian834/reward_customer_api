package com.rewardCustomer.dto.rewards;

import java.util.Date;

import lombok.Data;

@Data
public class RewardsDTO {

    private String customerID;
    private int dollarsSpent;
    private Date creationDate;

}
