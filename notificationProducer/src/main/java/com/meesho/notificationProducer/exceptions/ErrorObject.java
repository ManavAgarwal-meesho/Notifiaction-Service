package com.meesho.notificationProducer.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@Builder

public class ErrorObject {

    private HttpStatus statusCode;
    private String message;
    private Date timestamp;
    private List<String> errors;

}
