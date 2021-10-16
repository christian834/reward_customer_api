package com.rewardCustomer.service.rewards;

import com.rewardCustomer.dto.rewards.RewardsDTO;
import com.rewardCustomer.dto.rewards.RewardsResponseDTO;

public interface RewardsService {
 
    public Integer calculatePoints(Integer dollarsSpent);

}
