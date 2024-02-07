package com.meesho.notificationProducer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class CheckController {
    @GetMapping
    public ResponseEntity<Map<String, String>> hello(){

        Map<String, String> response = new HashMap<>();
        response.put("Status", "working");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
