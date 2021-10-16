package com.rewardCustomer.service.rewards.impl;

import com.rewardCustomer.service.rewards.RewardsService;

public class DefaultRewardsService implements RewardsService{

    public Integer calculatePoints(Integer dollarsSpent) {
        
        Integer pointsRewards = 0;

        if(dollarsSpent > 100){
            pointsRewards += (dollarsSpent - 100)*2 + 50;
        } else if(dollarsSpent > 50) {
            pointsRewards += dollarsSpent - 50;
        }

        return pointsRewards;
    }
    
}
