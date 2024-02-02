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

        RequestDatabase requestData = databaseRepository.save(data);
        return requestData.getRequestId();

    }

    // Fetch Data corresponding to a request
    public Optional<RequestDatabase> fetchById(String requestId){

        return databaseRepository.findById(requestId);

    }
}
