package com.meesho.notificationProducer.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class SendSMSRequestResponse extends ResponseObject {
    private String requestId;
    private String comments;
}
