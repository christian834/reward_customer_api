package com.rewardCustomer.dto.rewards;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RewardsDTO {

    private String customerID;
    private int dollarsSpent;
    private LocalDate  creationDate;

}
