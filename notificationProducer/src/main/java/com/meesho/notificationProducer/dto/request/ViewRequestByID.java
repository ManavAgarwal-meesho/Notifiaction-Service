package com.meesho.notificationProducer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ViewRequestByID {

    @JsonProperty("request_id")
    private String requestId;
}
