package com.rewardCustomer.controller.rewards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rewardCustomer.dto.rewards.RewardsDTO;
import com.rewardCustomer.dto.rewards.RewardsResponsePerMonthDATA;
import com.rewardCustomer.dto.rewards.RewardsResponseDTO;
import com.rewardCustomer.service.rewards.RewardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rewards")
public class RewardsController {

    private static final String PARAMS_ARE_EMPTY = "Params are empty";
    
    @Autowired
    private RewardsService rewardsService;

    @RequestMapping(value= "/customersPerMonth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RewardsResponseDTO> calculateRewardsCustomersPerMonth(@RequestBody(required = true) final List<RewardsDTO> rewardsDTOs){

        final RewardsResponseDTO rewardsResponseDTO = new RewardsResponseDTO();
    
        if(rewardsDTOs.isEmpty()){

            rewardsResponseDTO.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(PARAMS_ARE_EMPTY);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.BAD_REQUEST);
        }

        try {

            Map<Integer, RewardsResponsePerMonthDATA> rewardsResponsePerMonthDATA = new HashMap<Integer, RewardsResponsePerMonthDATA>();
            rewardsDTOs.stream()
                       .map(rewardsDTO -> 
                                        rewardsResponsePerMonthDATA.entrySet()
                                                                   .stream()
                                                                   .filter(rewardsResponsePerMonthDATA -> 
                                                                   rewardsResponsePerMonthDATA.getKey() == rewardsDTO.getCustomerID())):
           /* List<RewardsResponseDATA> rewardsResponseDATAs = rewardsDTOs.stream()
                                                                        .equals(r*/

        } catch(Exception e){

            rewardsResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new RewardsResponseDTO(), HttpStatus.OK);
        
    }

}
