package com.meesho.notificationProducer.controllers;

import com.meesho.notificationProducer.dto.request.AddOrRemoveBlacklistRequest;
import com.meesho.notificationProducer.dto.request.CheckIfBlacklistedRequest;
import com.meesho.notificationProducer.dto.response.AddOrRemoveBlacklistResponse;
import com.meesho.notificationProducer.dto.response.CheckIfBlacklistedResponse;
import com.meesho.notificationProducer.dto.response.FetchBlacklistResponse;
import com.meesho.notificationProducer.services.storageServices.RedisCacheServices;
import jakarta.validation.Valid;
import org.hibernate.mapping.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/blacklist")
public class BlacklistController {
    private final RedisCacheServices redisServices;

    Logger logger = LoggerFactory.getLogger(BlacklistController.class);

    @Autowired
    BlacklistController(RedisCacheServices redisServices) {
        this.redisServices = redisServices;
    }

    @PostMapping("/")
    public ResponseEntity<AddOrRemoveBlacklistResponse> addPhoneNumbersToBlacklist(@Valid @RequestBody AddOrRemoveBlacklistRequest blacklistRequest) {

        List<String> phoneNumbersToAdd = blacklistRequest.getPhoneNumbers();
        logger.info("Adding Phone numbers to Redis cache");

        redisServices.addNumbersToBlacklist(phoneNumbersToAdd);

        logger.info("Added Phone Numbers to Cache Successfully!!");

        AddOrRemoveBlacklistResponse response = AddOrRemoveBlacklistResponse.builder()
                .comments("Successfully Added to Blacklist")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<AddOrRemoveBlacklistResponse> removePhoneNumbersFromBlacklist(@Valid @RequestBody AddOrRemoveBlacklistRequest blacklistRequest) {
        List<String> phoneNumbersToRemove = blacklistRequest.getPhoneNumbers();
        logger.info("Removing Phone Numbers from Cache");

        redisServices.removeNumbersFromBlacklist(phoneNumbersToRemove);

        logger.info("Removed Phone Numbers from Cache Successfully!!");

        AddOrRemoveBlacklistResponse response = AddOrRemoveBlacklistResponse
                .builder()
                .comments("Successfully Added to Blacklist")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<FetchBlacklistResponse> fetchPhoneNumberBlacklist() {

        List<String> fetchedBlacklist = redisServices.getAllBlacklistedNumbers();

        FetchBlacklistResponse response = FetchBlacklistResponse.builder()
                .comments("Fetched Successfully!")
                .blacklist(fetchedBlacklist)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<CheckIfBlacklistedResponse> checkPhoneNumberIfExists(@Valid @RequestBody CheckIfBlacklistedRequest checkIfBlacklistedRequest) {

        String phoneNumber = checkIfBlacklistedRequest.getPhoneNumber();
        CheckIfBlacklistedResponse response = new CheckIfBlacklistedResponse(redisServices.checkIfBlacklisted(phoneNumber));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
