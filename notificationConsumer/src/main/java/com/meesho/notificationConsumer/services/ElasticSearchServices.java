package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.models.ESDocument;
import com.meesho.notificationConsumer.repository.ElasticSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchServices {

    @Autowired
    private ElasticSearchRepository ESRepository;

    Logger logger = LoggerFactory.getLogger(ElasticSearchRepository.class);

    public Boolean indexToElasticSearchDB (ESDocument esDocument) {
        try{
            ESRepository.save(esDocument);
            return Boolean.TRUE;
        }
        catch (Error err) {
            logger.error(err.getMessage());
        }
        return Boolean.FALSE;
    }
}
