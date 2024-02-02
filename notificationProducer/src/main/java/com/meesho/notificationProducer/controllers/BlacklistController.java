package com.meesho.notificationProducer.controllers;

import com.meesho.notificationProducer.dto.request.AddOrRemoveBlacklistRequest;
import com.meesho.notificationProducer.dto.request.CheckIfBlacklistedRequest;
import com.meesho.notificationProducer.dto.response.*;
import com.meesho.notificationProducer.services.storageServices.RedisCacheServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseObject> addPhoneNumbersToBlacklist(@Valid @RequestBody AddOrRemoveBlacklistRequest blacklistRequest) {

        try{
            List<String> phoneNumbersToAdd = blacklistRequest.getPhoneNumbers();
            logger.info("Adding Phone numbers to Redis cache");

            redisServices.addNumbersToBlacklist(phoneNumbersToAdd);

            logger.info("Added Phone Numbers to Cache Successfully!!");

            AddOrRemoveBlacklistResponse response = AddOrRemoveBlacklistResponse.builder()
                    .comments("Successfully Added to Blacklist")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Error error) {

            logger.error(error.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(error.toString())
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseObject> removePhoneNumbersFromBlacklist(@Valid @RequestBody AddOrRemoveBlacklistRequest blacklistRequest) {

        try{
            List<String> phoneNumbersToRemove = blacklistRequest.getPhoneNumbers();
            logger.info("Removing Phone Numbers from Cache");

            redisServices.removeNumbersFromBlacklist(phoneNumbersToRemove);

            logger.info("Removed Phone Numbers from Cache Successfully!!");

            AddOrRemoveBlacklistResponse response = AddOrRemoveBlacklistResponse.builder()
                    .comments("Successfully Removed from Blacklist")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Error error){
            logger.error(error.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(error.toString())
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> fetchPhoneNumberBlacklist() {
        try{
            List<String> fetchedBlacklist = redisServices.getAllBlacklistedNumbers();

            logger.info("fetched Blacklist : {}", fetchedBlacklist);

            FetchBlacklistResponse response = FetchBlacklistResponse.builder()
                    .comments("Fetched Successfully!")
                    .blacklist(fetchedBlacklist)
                    .build();


            return ResponseEntity.ok(response);
        }
        catch (Error error) {
            logger.error(error.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(error.toString())
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<ResponseObject> checkPhoneNumberIfExists(@Valid @RequestBody CheckIfBlacklistedRequest checkIfBlacklistedRequest) {
        try{
            String phoneNumber = checkIfBlacklistedRequest.getPhoneNumber();
            CheckIfBlacklistedResponse response = new CheckIfBlacklistedResponse(redisServices.checkIfBlacklisted(phoneNumber));

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Error error) {
            logger.error(error.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(error.toString())
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
