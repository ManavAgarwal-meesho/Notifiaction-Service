package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.models.RequestDatabase;
import com.meesho.notificationConsumer.repository.RequestDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SQLDatabaseServices {

    Logger logger = LoggerFactory.getLogger(SQLDatabaseServices.class);

    @Autowired
    private RequestDatabaseRepository databaseRepository;

    // Update Status of Request
    public Boolean updateStatusOnSuccess(String requestId){
        try{
            String requestSuccessful = Constants.DB_REQUEST_SUCCESSFUL;
            String requestSuccessfulComment = Constants.DB_REQUEST_SUCCESSFUL;

            databaseRepository.updateRequest(requestId, requestSuccessfulComment, requestSuccessful);

            logger.info("Status Update Successful");
            return Boolean.TRUE;
        } catch (Error err) {

            logger.error(err.getMessage());
            return Boolean.FALSE;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Boolean.FALSE;
        }

    }

    public Boolean updateStatusOnFailure(String requestId, String failureComment){
        try{
            String requestFailure = Constants.DB_REQUEST_FAILURE;

            databaseRepository.updateRequest(requestId, failureComment, requestFailure);

            logger.info("Status Update Successful");
            return Boolean.TRUE;
        }
        catch (Error err) {

            logger.error(err.getMessage());
            return Boolean.FALSE;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Boolean.FALSE;
        }

    }
}
