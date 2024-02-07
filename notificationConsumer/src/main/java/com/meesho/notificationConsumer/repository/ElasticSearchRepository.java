package com.meesho.notificationConsumer.repository;

import com.meesho.notificationConsumer.models.ESDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<ESDocument, String> {

}
