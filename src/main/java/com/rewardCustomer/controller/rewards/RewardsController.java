package com.rewardCustomer.controller.rewards;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rewardCustomer.dto.rewards.RewardsDTO;
import com.rewardCustomer.dto.rewards.RewardsResponseDATA;
import com.rewardCustomer.dto.rewards.RewardsResponseDTO;
import com.rewardCustomer.service.rewards.RewardsService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rewards")
public class RewardsController {

    private static final Logger LOGGER = Logger.getLogger(RewardsController.class);

    private static final String PARAMS_ARE_EMPTY_MSG = "Params are empty";
    private static final String EMPTY_FIELDS_MSG = "Some fields are empty";
    private static final String INTERNAL_ERROR_MSG = "An internal error has ocurred";

    @Autowired
    private RewardsService rewardsService;

    @RequestMapping(value = "/customersPerMonth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RewardsResponseDTO> calculateRewardsCustomersPerMonth(
            @RequestBody(required = true) final List<RewardsDTO> rewardsDTOs) {

        final RewardsResponseDTO rewardsResponseDTO = new RewardsResponseDTO();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Starting procces for customer per month");
        }

        if (rewardsDTOs.isEmpty()) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info(PARAMS_ARE_EMPTY_MSG);
            }

            rewardsResponseDTO.setStatus(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(PARAMS_ARE_EMPTY_MSG);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.NOT_ACCEPTABLE);
        }

        try {

            Map<String, List<RewardsResponseDATA>> rewardsResponseDATA = rewardsDTOs.stream()
                    .collect(Collectors.groupingBy(
                            rewardsDTO -> Arrays.asList(rewardsDTO.getCustomerID(),
                                    rewardsDTO.getCreationDate().withDayOfMonth(1).toString()),
                            Collectors.summingInt(
                                    rewardsDTO -> rewardsService.calculatePoints(rewardsDTO.getDollarsSpent()))))
                    .entrySet().stream().collect(
                            Collectors.groupingBy(rewardsDTOMap -> rewardsDTOMap.getKey().get(0),
                                    Collectors.mapping(
                                            rewardsDTOMap -> new RewardsResponseDATA(rewardsDTOMap.getValue(),
                                                    LocalDate.parse(rewardsDTOMap.getKey().get(1))),
                                            Collectors.toList())));

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Rewards points calculated sucesfully");
            }
            rewardsResponseDTO.setStatus(HttpStatus.OK.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(StringUtils.EMPTY);
            rewardsResponseDTO.setData(rewardsResponseDATA);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.OK);
 
        } catch (Exception e) {

            LOGGER.error(e);
            rewardsResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(INTERNAL_ERROR_MSG);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/customersTotal", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RewardsResponseDTO> calculateRewardsCustomersTotal(
            @RequestBody(required = true) final List<RewardsDTO> rewardsDTOs) {

        final RewardsResponseDTO rewardsResponseDTO = new RewardsResponseDTO();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Starting procces for customer total");
        }

        if (rewardsDTOs.isEmpty()) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info(PARAMS_ARE_EMPTY_MSG);
            }

            rewardsResponseDTO.setStatus(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(PARAMS_ARE_EMPTY_MSG);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.BAD_REQUEST);
        }

        try {

            Map<String, List<RewardsResponseDATA>> rewardsResponseDATA = rewardsDTOs.stream()
                    .collect(Collectors.groupingBy(RewardsDTO::getCustomerID,
                            Collectors.summingInt(
                                    rewardsDTO -> rewardsService.calculatePoints(rewardsDTO.getDollarsSpent()))))
                    .entrySet().stream()
                    .collect(Collectors.groupingBy(rewardsDTOMap -> rewardsDTOMap.getKey(),
                            Collectors.mapping(rewardsDTOMap -> new RewardsResponseDATA(rewardsDTOMap.getValue(), null),
                                    Collectors.toList())));

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Rewards points calculated sucesfully");
            }
            rewardsResponseDTO.setStatus(HttpStatus.OK.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(StringUtils.EMPTY);
            rewardsResponseDTO.setData(rewardsResponseDATA);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.OK);

        } catch (Exception e) {

            LOGGER.error(e);
            rewardsResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(INTERNAL_ERROR_MSG);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RewardsResponseDTO> calculateRewardsCustomersTotal(
            @RequestBody(required = true) final RewardsDTO rewardsDTO) {

        final RewardsResponseDTO rewardsResponseDTO = new RewardsResponseDTO();

        try {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Starting procces for customerID: " + rewardsDTO.getCustomerID());
            }

            RewardsResponseDATA rewardsResponseDATA = new RewardsResponseDATA(
                    rewardsService.calculatePoints(rewardsDTO.getDollarsSpent()), rewardsDTO.getCreationDate());

            Map<String, List<RewardsResponseDATA>> rewardsResponseDATA_MAP = new HashMap<>();

            rewardsResponseDATA_MAP.put(rewardsDTO.getCustomerID(), Arrays.asList(rewardsResponseDATA));

            rewardsResponseDTO.setStatus(HttpStatus.OK.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(StringUtils.EMPTY);
            rewardsResponseDTO.setData(rewardsResponseDATA_MAP);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.OK);

        } catch (Exception e) {

            LOGGER.error(e);
            rewardsResponseDTO.setStatus(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
            rewardsResponseDTO.setErrorMessage(EMPTY_FIELDS_MSG);
            return new ResponseEntity<>(rewardsResponseDTO, HttpStatus.NOT_ACCEPTABLE);

        } 

    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
