package com.rewardCustomer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rewardCustomer.controller.rewards.RewardsController;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileReader;

@AutoConfigureMockMvc
@SpringBootTest
class RewardsControllerIT {

    private static final String PARAMS_ARE_EMPTY_MSG = "Params are empty";

    @Autowired
    private RewardsController rewardsController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(rewardsController).isNotNull();
    }

    @Test
    public void testRewardsEmptyCustomersPerMonth() throws Exception{

        JSONParser parser = new JSONParser();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                          .post("/rewards/customersPerMonth")
                                          .content("[]")
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andExpect(status().isNotAcceptable())
                                          .andReturn();

        Object jsonResponse = parser.parse(result.getResponse().getContentAsString());

        JSONObject jsonObject = (JSONObject) jsonResponse;

        String errorMessage = (String)jsonObject.get("errorMessage");
 
        assertNotNull(errorMessage);
        assertEquals(PARAMS_ARE_EMPTY_MSG, errorMessage);

    }

    @Test
    public void testRewardsEmptyCustomersTotal() throws Exception{

        JSONParser parser = new JSONParser();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                          .post("/rewards/customersTotal")
                                          .content("[]")
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andExpect(status().isNotAcceptable())
                                          .andReturn();

        Object jsonResponse = parser.parse(result.getResponse().getContentAsString());

        JSONObject jsonObject = (JSONObject) jsonResponse;

        String errorMessage = (String)jsonObject.get("errorMessage");
 
        assertNotNull(errorMessage);
        assertEquals(PARAMS_ARE_EMPTY_MSG, errorMessage);

    }


    @Test
    public void testRewardsCustomersPerMonth() throws Exception{

        JSONParser parser = new JSONParser();

        File file  = ResourceUtils.getFile("classpath:dataSet.json");
        
        Object jsonFile = parser.parse(new FileReader(file));

        JSONArray  jsonObjectFile = (JSONArray) jsonFile;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                          .post("/rewards/customersPerMonth")
                                          .content(jsonObjectFile.toJSONString())
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andExpect(status().isOk())
                                          .andReturn();

        Object jsonResponse = parser.parse(result.getResponse().getContentAsString());

        JSONObject jsonObject = (JSONObject) jsonResponse;

        String status = (String)jsonObject.get("status");
 
        assertNotNull(status);
        assertEquals("OK", status);

    }

    @Test
    public void testRewardsCustomersTotal() throws Exception{

        JSONParser parser = new JSONParser();

        File file  = ResourceUtils.getFile("classpath:dataSet.json");
        
        Object jsonFile = parser.parse(new FileReader(file));

        JSONArray  jsonObjectFile = (JSONArray) jsonFile;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                          .post("/rewards/customersTotal")
                                          .content(jsonObjectFile.toJSONString())
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andExpect(status().isOk())
                                          .andReturn();

        Object jsonResponse = parser.parse(result.getResponse().getContentAsString());

        JSONObject jsonObject = (JSONObject) jsonResponse;

        String status = (String)jsonObject.get("status");
 
        assertNotNull(status);
        assertEquals("OK", status);

    }

    @Test
    public void testRewardsCustomer() throws Exception{

        JSONParser parser = new JSONParser();

        String jsonCustomer = "{\"customerID\": \"12315\",\"dollarsSpent\" : 120,\"creationDate\" : \"2021-10-20\" }";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                          .post("/rewards/customer")
                                          .content(jsonCustomer)
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andExpect(status().isOk())
                                          .andReturn();

        Object jsonResponse = parser.parse(result.getResponse().getContentAsString());

        JSONObject jsonObject = (JSONObject) jsonResponse;

        String status = (String)jsonObject.get("status");
 
        assertNotNull(status);
        assertEquals("OK", status);

    }

}
