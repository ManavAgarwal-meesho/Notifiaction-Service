package com.meesho.notificationProducer.services.storageServices;

import com.meesho.notificationProducer.models.RequestDatabase;
import com.meesho.notificationProducer.repository.RequestDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SQLDatabaseServices {

    @Autowired
    private RequestDatabaseRepository databaseRepository;

    // Store Request in SQL Table
    public String storeData(RequestDatabase data){

        RequestDatabase smsRequest = databaseRepository.save(data);
        return smsRequest.getRequestId();

    }

    // Update Status of Request
    public void updateStatusSuccess(){
        return;
    }

    public void updateStatusFailure(){
        return;
    }

    // Fetch Data corresponding to a request
    public Optional<RequestDatabase> fetchById(String requestId){

        return databaseRepository.findById(requestId);

    }
}
