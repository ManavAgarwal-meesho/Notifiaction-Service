package com.meesho.notificationProducer.controllers;

import com.meesho.notificationProducer.dto.request.SendSMSRequest;
import com.meesho.notificationProducer.dto.request.ViewRequestByID;
import com.meesho.notificationProducer.dto.response.ViewRequestByIDResponse;
import com.meesho.notificationProducer.models.RequestDatabase;
import com.meesho.notificationProducer.services.KafkaProducerService;
import com.meesho.notificationProducer.services.storageServices.RedisCacheServices;
import com.meesho.notificationProducer.services.storageServices.SQLDatabaseServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/sms")
public class SMSRequestController {

    private final KafkaProducerService producer;
    private final SQLDatabaseServices sqlServices;
    private final RedisCacheServices  redisServices;

    Logger logger = LoggerFactory.getLogger(SMSRequestController.class);

    @Autowired
    SMSRequestController(KafkaProducerService producer, SQLDatabaseServices sqlServices, RedisCacheServices redisServices) {
        this.producer      = producer;
        this.sqlServices   = sqlServices;
        this.redisServices = redisServices;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendSMS(@Valid @RequestBody SendSMSRequest payload) {

        /* Step 0 - Extract data from payload */
        String phoneNumber = payload.getPhoneNumber();
        String message     = payload.getMessage();

        /* Step 1 - Check weather the Phone Number is Blacklisted */
        if(Boolean.TRUE.equals(redisServices.checkIfBlacklisted(phoneNumber))){

            Map<String, String> response = new HashMap<>();
            response.put("Comments", "Error sending message! Phone Number is Blacklisted");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        /* Step 2 - Create an SMS Request Object */
        RequestDatabase smsRequest = RequestDatabase.builder()
                .phoneNumber(phoneNumber)
                .message(message)
                .build();

        /* Step 3 - Store Object in DB and retrieve req_id */
        String requestId = sqlServices.storeData(smsRequest);

        /* Step 4 - Publish Request to Kafka Topic */
        producer.publishToTopic(requestId);

        Map<String, String> response = new HashMap<>();
        response.put("Comments", "Successfully Sent");
        response.put("request_id", requestId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/view/{request_id}")
    public ResponseEntity<Map<String, Object>> fetchSMS(@Valid @RequestBody ViewRequestByID payload) {

        String requestId = payload.getRequestId();
        Optional<RequestDatabase> fetchedSMSRequest = sqlServices.fetchById(requestId);
        Map<String, Object> response = new HashMap<>();

        if(fetchedSMSRequest.isPresent()){

            ViewRequestByIDResponse fetchedData = new ViewRequestByIDResponse(fetchedSMSRequest.get());
            response.put("data", fetchedData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }

        Error error = new Error("INVALID_REQUEST");
        response.put("error",error);

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}