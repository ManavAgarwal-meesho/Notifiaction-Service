package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.models.RequestDatabase;
import com.meesho.notificationConsumer.repository.RequestDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SQLDatabaseServices {

    @Autowired
    private RequestDatabaseRepository databaseRepository;

    // Update Status of Request
    public void updateStatusOnSuccess(String requestId){
        return;
    }

    public void updateStatusOnFailure(String requestId, String failureComment){
        return;
    }
}
