package com.meesho.notificationProducer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meesho.notificationProducer.constants.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SendSMSRequest {

    @JsonProperty("phone_number")
    @Pattern(regexp= Constants.PHONE_NUMBER_REGEX ,message = Constants.INVAlID_PHONE_NUMBER)
    private String phoneNumber;

    @JsonProperty("message")
    @NotBlank(message = "Message Field cannot be Empty")
    private String message;
}
