package com.rewardCustomer.service.rewards;

import org.springframework.stereotype.Repository;

@Repository
public interface RewardsService {
 
    public Integer calculatePoints(Integer dollarsSpent);

}
