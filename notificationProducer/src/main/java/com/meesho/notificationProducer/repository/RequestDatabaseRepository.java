package com.meesho.notificationProducer.repository;

import com.meesho.notificationProducer.models.RequestDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDatabaseRepository extends JpaRepository<RequestDatabase, String> {
}
