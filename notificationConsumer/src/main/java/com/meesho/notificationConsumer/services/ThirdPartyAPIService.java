package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.models.RequestDatabase;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyAPIService {

    public Boolean sendSMSAPI(RequestDatabase requestData){

        return Boolean.TRUE;
    }

}
