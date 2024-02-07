package com.meesho.notificationProducer.repository;

import com.meesho.notificationProducer.models.ESDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<ESDocument, String> {

    public Page<ESDocument> findByMessageContaining(String keyword, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"range\": {\"time\": {\"gte\": \"?0\", \"lte\": \"?1\"}}},{\"match\": {\"phone_number\": \"?2\"}}]}}")
    public Page<ESDocument> findByPhoneNumberAndCreatedAtBetween(String startTime, String endTime,String phoneNumber, Pageable pageable);

}
