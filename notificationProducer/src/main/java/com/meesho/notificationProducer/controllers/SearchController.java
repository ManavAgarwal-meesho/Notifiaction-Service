package com.meesho.notificationProducer.controllers;

import com.meesho.notificationProducer.dto.request.SearchESbyPhoneNumber;
import com.meesho.notificationProducer.dto.request.SearchESbyText;
import com.meesho.notificationProducer.dto.response.ErrorResponse;
import com.meesho.notificationProducer.dto.response.ResponseObject;
import com.meesho.notificationProducer.dto.response.SearchESResponse;
import com.meesho.notificationProducer.models.ESDocument;
import com.meesho.notificationProducer.services.storageServices.ElasticSearchServices;
import com.meesho.notificationProducer.utils.ConvertStringTimeToDates;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/search")
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ElasticSearchServices esServices;


    @GetMapping("/text")
    public ResponseEntity<ResponseObject> searchForText(@Valid @RequestBody SearchESbyText payload, @RequestParam Integer page) {

        try{

            List<ESDocument> result = esServices.getDocumentsByKeyword(payload.getKeyword(), page);
            logger.info(result.toString());

            SearchESResponse response = SearchESResponse.builder()
                    .documents(result)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Error error) {
                logger.error(error.toString());
                ErrorResponse response = ErrorResponse.builder()
                        .errorComment(error.toString())
                        .build();

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (IOException e) {
            logger.error(e.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(e.toString())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/phone")
    public ResponseEntity<ResponseObject> searchForPhoneNumberInRange(@Valid @RequestBody SearchESbyPhoneNumber payload, @RequestParam Integer page) {

        try{

            String startTime = ConvertStringTimeToDates.convertStringTimestampToDate(payload.getStartTime());
            String endTime = ConvertStringTimeToDates.convertStringTimestampToDate(payload.getEndTime());

            String phoneNumber = payload.getPhoneNumber();

            List<ESDocument> res = esServices.getDocumentsByPhoneNumber(phoneNumber, startTime, endTime, page);
            logger.info(res.toString());

            SearchESResponse response = SearchESResponse.builder()
                    .documents(res)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Error error) {
            logger.error(error.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(error.toString())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (IOException e) {

            logger.error(e.toString());
            ErrorResponse response = ErrorResponse.builder()
                    .errorComment(e.toString())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
