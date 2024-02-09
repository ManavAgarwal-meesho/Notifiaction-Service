package com.meesho.notificationProducer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meesho.notificationProducer.constants.Constants;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckIfBlacklistedRequest {

    @JsonProperty("phone_number")
    @Pattern(regexp= Constants.PHONE_NUMBER_REGEX , message = Constants.INVAlID_PHONE_NUMBER)
    private String phoneNumber;

}
