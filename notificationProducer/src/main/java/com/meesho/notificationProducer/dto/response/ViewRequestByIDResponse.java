package com.meesho.notificationProducer.dto.response;

import com.meesho.notificationProducer.models.RequestDatabase;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ViewRequestByIDResponse extends ResponseObject {

    private RequestDatabase smsRequest;
    private String comments;

}
