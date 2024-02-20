package com.meesho.notificationConsumer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class APIResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("transid")
    private String transid;

    @JsonProperty("description")
    private String description;

    @JsonProperty("correlationid")
    private String correlationid;

}
