package com.rewardCustomer.service.rewards;

import org.springframework.stereotype.Repository;

@Repository
public interface RewardsService {

    /**
     * Calculate reward points based in dollar spends
     * @param dollarsSpent 
     * @return Reward points
     */
    public Integer calculatePoints(Integer dollarsSpent);

}
