package com.meesho.notificationConsumer.dto.request.thirdPatyAPI;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class RequestBodyDestination {

    private ArrayList<String> msisdn;
    private String correlationid;

}
