package com.meesho.notificationProducer.services.storageServices;

import com.meesho.notificationProducer.constants.Constants;
import com.meesho.notificationProducer.models.ESDocument;
import com.meesho.notificationProducer.repository.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ElasticSearchServices {

    @Autowired
    ElasticSearchRepository esRepository;

    public List<ESDocument> getDocumentsByKeyword(String keyword, Integer page) throws IOException {
        try{
            Pageable pageable = PageRequest.of(page, Constants.ELASTIC_SEARCH_PAGE_SIZE);
            return esRepository.findByMessageContaining(keyword, pageable).getContent();
        } catch (Error e) {
            throw new RuntimeException();
        } catch (Exception ex) {
            throw new IOException();
        }
    }

    public List<ESDocument> getDocumentsByPhoneNumber(String phoneNumber, String startTime, String endTime, Integer page) throws IOException {
        try{
            Pageable pageable = PageRequest.of(page, Constants.ELASTIC_SEARCH_PAGE_SIZE);
            return esRepository.findByPhoneNumberAndCreatedAtBetween(startTime,endTime, phoneNumber, pageable).getContent();
        } catch (Error e) {
            throw new RuntimeException();
        } catch (Exception ex) {
            throw new IOException();
        }
    }

    public Iterable<ESDocument> findAllDocuments() {
        return esRepository.findAll();
    }
}
