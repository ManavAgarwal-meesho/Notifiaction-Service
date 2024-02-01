package com.meesho.notificationProducer.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString

public class CheckIfBlacklistedResponse {

    private Boolean isBlacklisted;

}
