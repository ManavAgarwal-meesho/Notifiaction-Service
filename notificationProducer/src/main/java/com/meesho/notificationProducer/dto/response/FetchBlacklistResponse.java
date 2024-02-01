package com.meesho.notificationProducer.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Builder

public class FetchBlacklistResponse {

    private String comments;
    private List<String> blacklist;

}
