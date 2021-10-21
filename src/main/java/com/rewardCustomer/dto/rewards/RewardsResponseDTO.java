package com.rewardCustomer.dto.rewards;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RewardsResponseDTO {

    private String status;
    private String errorMessage;
    private Map<String, List<RewardsResponseDATA>> data;
    
}
