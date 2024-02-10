package com.meesho.notificationProducer.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class CheckIfBlacklistedResponse extends ResponseObject {
    private Boolean isBlacklisted;
}
