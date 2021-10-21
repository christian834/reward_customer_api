package com.rewardCustomer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rewardCustomer.service.rewards.RewardsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RewardsServiceTest {
    
    @Autowired
    private RewardsService rewardsService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(rewardsService).isNotNull();
    }

    @Test 
    void testDollarsSpentsGreaterThan100() throws Exception{

        Integer result = rewardsService.calculatePoints(120);
        assertEquals(90, result);
    }

    @Test 
    void testDollarsSpentsGreaterThan50() throws Exception{

        Integer result = rewardsService.calculatePoints(90);
        assertEquals(40, result);
    }

    @Test 
    void testDollarsSpentsSmallerThan50() throws Exception{

        Integer result = rewardsService.calculatePoints(30);
        assertEquals(0, result);
    }
}
