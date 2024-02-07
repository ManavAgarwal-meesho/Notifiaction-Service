package com.meesho.notificationProducer.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ErrorResponse extends ResponseObject {
    private String errorComment;
    private StackTraceElement[] errorStack;
}
