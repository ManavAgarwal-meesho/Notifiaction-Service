package com.meesho.notificationConsumer.dto.request.thirdPatyAPI;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class RequestBodyTemplate {

    private String deliverychannel;
    private RequestBodyChannel channels;
    private ArrayList<RequestBodyDestination> destination;

}
