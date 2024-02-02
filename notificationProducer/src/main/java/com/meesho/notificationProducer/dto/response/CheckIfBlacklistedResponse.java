package com.meesho.notificationProducer.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CheckIfBlacklistedResponse extends ResponseObject {
    private Boolean isBlacklisted;
}
