package com.meesho.notificationConsumer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ThirdPartyAPIResponse {

    @JsonProperty("response")
    private List<APIResponse> response;

}
