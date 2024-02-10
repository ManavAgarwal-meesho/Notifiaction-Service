package com.meesho.notificationProducer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchESbyPhoneNumber {

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;
}
