package com.meesho.notificationProducer.dto.response;

import com.meesho.notificationProducer.models.RequestDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString

public class ViewRequestByIDResponse {

    private RequestDatabase smsRequest;

}
