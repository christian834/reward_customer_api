package com.rewardCustomer.controller.rewards;

import java.util.ArrayList;
import java.util.List;

import com.rewardCustomer.dto.rewards.RewardsDTO;
import com.rewardCustomer.dto.rewards.RewardsResponseDTO;
import com.rewardCustomer.service.rewards.RewardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rewards")
public class RewardsController {
    
    @Autowired
    private RewardsService rewardsService;

    @RequestMapping(value= "/customers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<RewardsResponseDTO> calculateRewardsCustomers(@RequestBody(required = true) final List<RewardsDTO> rewardsDTOs){

        return new ArrayList<RewardsResponseDTO>();
        
    }

}
