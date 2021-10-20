package com.rewardCustomer.service.rewards.impl;

import com.rewardCustomer.service.rewards.RewardsService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DefaultRewardsService implements RewardsService{

    private static final Logger LOGGER = Logger.getLogger(DefaultRewardsService.class);

    private static final String DOLLARS_SPENTS_ARE_GREATER_THAN = "Dollars spents are greater than ";
    
    public Integer calculatePoints(Integer dollarsSpent) {
        
        Integer pointsRewards = 0;

        if(dollarsSpent > 100){

            if(LOGGER.isDebugEnabled()){

                LOGGER.info(DOLLARS_SPENTS_ARE_GREATER_THAN + "100");
            }
            pointsRewards += (dollarsSpent - 100)*2 + 50;

        } else if(dollarsSpent > 50) {

            if(LOGGER.isDebugEnabled()){

                LOGGER.info(DOLLARS_SPENTS_ARE_GREATER_THAN + "50");
            }
            pointsRewards += dollarsSpent - 50;
        }

        return pointsRewards;
    }
    
}
