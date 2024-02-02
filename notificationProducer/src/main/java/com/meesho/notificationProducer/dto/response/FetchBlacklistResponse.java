package com.meesho.notificationProducer.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class FetchBlacklistResponse extends ResponseObject {

    private String comments;
    private List<String> blacklist;

}
